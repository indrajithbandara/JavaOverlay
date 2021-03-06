package com.dim.javaoverlay;


/**
 * Created by dim on 17/2/19.
 */

public class JavaOverlayExtension {

    public List<Exclude> ruleList = new ArrayList<>();
    private Map<String, Exclude> map = new HashMap<>();

    public JavaOverlayExtension excludeClass(String excludeClass) {

        String[] split = excludeClass.split(":");
        if (split.length == 3) {
            Exclude exclude = map.get(split[0] + split[1]);
            if (exclude == null) {
                exclude = new Exclude(split[0], split[1]);
                ruleList.add(exclude);
                map.put(split[0] + split[1], exclude);
            }
            exclude.add(split[2]);
        }
        return this;
    }

    public static class Exclude {
        public String group;
        public String jarName;
        public List<String> classNameList = new ArrayList<>();

        public Exclude(final String group, final String jarName) {
            this.group = group;
            this.jarName = jarName;
        }

        public void add(String className) {
            classNameList.add(className);
        }

        public boolean isMatch(final String file) {
            return file.contains("/" + group + "/") && file.contains("/" + jarName + "/");
        }

        public boolean isMatchClass(final String name) {
            return classNameList.contains(name);
        }

        @Override
        public String toString() {
            return "Exclude{" +
                    "group='" + group + '\'' +
                    ", jarName='" + jarName + '\'' +
                    ", classNameList=" + classNameList +
                    '}';
        }
    }
}
