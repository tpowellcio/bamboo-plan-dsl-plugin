package ch.mibex.bamboo.plandsl.dsl

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class NullBambooFacade implements BambooFacade {
    private final BambooEnvironment environment

    @Deprecated
    NullBambooFacade() {
        environment = new NullBambooEnvironment()
    }

    NullBambooFacade(Map<String, String> environment) {
        this.environment = new KeyValueEnv(environment)
    }

    static class KeyValueEnv implements BambooEnvironment {
        private final Map<String, String> env

        KeyValueEnv(Map<String, String> environment) {
            this.env = environment
        }

        @Override
        String getAt(String key) {
            String value = env[key]
            Validations.requireNotNullOrEmpty(value,
                    "No env variable found for '$key' in ${env.keySet().join(',')}")
        }

        @Override
        String call(String key) {
            getAt(key)
        }
    }

    @Override
    void requirePlugin(String pluginKey) {
        // nop
    }

    @Override
    void requireMinimumPluginVersion(String pluginKey, String minVersion) {
        // nop
    }

    @Override
    void requireGlobalVariable(String variableName) {
        // nop
    }

    @Override
    void requireExecutable(String executableName) {
        // nop
    }

    @Override
    void requirePlan(String planKey) {
        // nop
    }

    @Override
    void requireJdk(String jdkName) {
        // nop
    }

    @Override
    void requireLinkedRepository(String repoName) {
        // nop
    }

    @Override
    void requireBambooVersion(String version) {
        // nop
    }

    @Override
    void requireSharedCredentials(String name) {
        // nop
    }

    @Override
    void requireApplicationLink(String name) {
        // nop
    }

    @Override
    Map<String, Object> getExportedBambooObjects() {
        [:]
    }

    @Override
    String encrypt(String text) {
        text
    }

    @Override
    void log(String message) {
        // nop
    }

    @Override
    Logger getBuildLogger() {
        new NullLogger()
    }

    @Override
    BambooEnvironment getVariableContext() {
        environment
    }

    // this is used to allow checking of DSL scripts without throwing errors
    static class NullBambooEnvironment implements BambooEnvironment {
        @Override
        String getAt(String key) {
            key
        }

        @Override
        String call(String key) {
            key
        }
    }

    static class NullLogger implements Logger {

        @Override
        void println(String s) {
            //nop
        }

        @Override
        void info(String s) {
            //nop
        }

        @Override
        void error(String s) {
            //nop
        }
    }

}

