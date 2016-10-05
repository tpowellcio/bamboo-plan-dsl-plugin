package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class CustomTask extends Task {
    Map<String, String> buildConfig = [:]

    CustomTask(String pluginKey) {
        super(pluginKey)
    }

    def methodMissing(String methodName, args) {
        buildConfig << [(methodName): args[0].toString()]
    }

    def configure(Map<String, Object> config) {
        config.each { k, v -> buildConfig << [(k): v.toString()] }
    }

    @Override
    Map<String, String> getConfig(Map<Object, Object> context) {
        buildConfig
    }
}
