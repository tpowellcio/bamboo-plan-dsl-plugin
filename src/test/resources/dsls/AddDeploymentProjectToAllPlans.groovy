package dsls

[['ACTIVITIES', 'Activity Streams for Bitbucket Server'], ['PLANDSL', 'Plan DSL for Bamboo']].each { planInfos ->
    project("ATLAS") {
        plan(planInfos[0]) {
            deploymentProject(planInfos[1]) {
                description "Deployment project for plug-in"
                environment("Integration") {
                    description "Deploy plug-in to integration server"

                    tasks {
                        cleanWorkingDirectory("Clean the working directory") {}

                        artifactDownload("Download release contents") {
                            artifact("plug-in") {
                            }
                        }
                    }
                }
            }
        }
    }
}

