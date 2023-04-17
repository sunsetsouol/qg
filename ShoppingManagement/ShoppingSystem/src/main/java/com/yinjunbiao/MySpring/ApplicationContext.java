package com.yinjunbiao.MySpring;


import com.yinjunbiao.MySpring.Annotation.*;
import com.yinjunbiao.util.CONST;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ApplicationContext接口
 * @author yinjunbiao
 */
public class ApplicationContext {

    private Class configClass;

    private static HashMap<Class,BeanFactory> beanFactoryMaps = new HashMap<>();

    private static HashMap<String,Object> earlySingletonObjects = new HashMap<>();

    private static ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<String> beanPostProcessorList = new ArrayList<>();

    public ApplicationContext(Class configClass) {
        this.configClass = configClass;

        scan(configClass);

        preInstantiateSingletons();

    }

    public static void registerFactory(BeanFactory beanFactory){
        beanFactoryMaps.put(beanFactory.getObjectType(),beanFactory);
    }

    /**
     * 实例化单例bean对象
     */
    private void preInstantiateSingletons() {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals(CONST.SINGLETON)) {
                if (!singletonObjects.containsKey(beanName)){
                    Object bean = createBean(beanName, beanDefinition);
                    singletonObjects.put(beanName, bean);
                    Field[] declaredFields = beanDefinition.getClazz().getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        if (declaredField.getAnnotation(Autowired.class) != null){
                            try {
                                System.out.println(declaredField.getName());
                                declaredField.setAccessible(true);
                                declaredField.set(bean,getBean(declaredField.getName()));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * 根据包扫描路径扫描对应的包
     *
     * @param configClass
     */
    private void scan(Class configClass) {
        ComponentScan componentScnaAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        if (componentScnaAnnotation != null) {
            String path = componentScnaAnnotation.value();

            ClassLoader classLoader = this.getClass().getClassLoader();
            URL resource = classLoader.getResource(path.replace(".", "/"));
            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    scanFile(f,classLoader);
                }
            }

        }

    }

    private void scanFile(File file,ClassLoader classLoader){
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                scanFile(f,classLoader);
            }
            return;
        }
        String fileName = file.getAbsolutePath();
        if (fileName.endsWith(".class")) {
            String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
            className = className.replace("\\", ".");
            try {
                Class<?> aClass = classLoader.loadClass(className);
                if (aClass.isAnnotationPresent(Component.class)) {
                    //如果是一个bean，初始化beanDefinition

                    Component componentAnnotation = aClass.getDeclaredAnnotation(Component.class);
                    String beanName = componentAnnotation.value();
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setClazz(aClass);
                    if (aClass.isAnnotationPresent(Scope.class)) {
                        Scope scopeAnnotaion = aClass.getDeclaredAnnotation(Scope.class);
                        beanDefinition.setScope(scopeAnnotaion.value());
                    } else {
                        beanDefinition.setScope(CONST.SINGLETON);
                    }

                    //如果是接口，设置beanDefinition的interface为true
                    if (aClass.isAnnotationPresent(Mapper.class)){
                        beanDefinition.setMapper(true);
                    }
                    beanDefinitionMap.put(beanName, beanDefinition);
                    if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                        beanPostProcessorList.add(beanName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建bean对象
     *
     * @param beanName bean的名字
     * @param beanDefinition bean对应的beanDefinition
     * @return 返回创建的bean对象
     */
    public Object createBean(String beanName, BeanDefinition beanDefinition) {
        try {
            Class clazz = beanDefinition.getClazz();
            Object instance = null;
            //beanDefinition获取class
            if (beanDefinition.isMapper()){
                instance = beanFactoryMaps.get(clazz).getObject();
            }else {
                instance = clazz.getDeclaredConstructor().newInstance();
            }
            earlySingletonObjects.put(beanName,instance);
            //依赖注入
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance, bean);
                }
            }
            //回调函数设置对象的beanName
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            //初始化前
            for (String s : beanPostProcessorList) {
                BeanPostProcessor beanPostProcessor = (BeanPostProcessor) getBean(s);
                if (beanPostProcessor != null) {
                    instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
                }
            }
            //初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }

            //初始化后
            for (String s : beanPostProcessorList) {
                BeanPostProcessor beanPostProcessor = ((BeanPostProcessor) getBean(s));
                if (beanPostProcessor != null) {
                    instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
                }
            }
            earlySingletonObjects.remove(beanName);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals(CONST.SINGLETON)) {
                //单例从单例池获取
                if (!singletonObjects.containsKey(beanName)){
                    if (earlySingletonObjects.containsKey(beanName)){
                        //出现循环依赖
                        return earlySingletonObjects.get(beanName);
                    }else {
                        Object bean = createBean(beanName, beanDefinition);
                        singletonObjects.put(beanName,bean);
                    }

                }
                return singletonObjects.get(beanName);
            } else {
                //多例再create一个Bean
                return createBean(beanName, beanDefinition);
            }
        } else {
            throw new NullPointerException("没有找到需要的bean对象");
        }
    }
}
