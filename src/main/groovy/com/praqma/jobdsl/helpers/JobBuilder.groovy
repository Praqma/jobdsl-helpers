package com.praqma.jobdsl.helpers

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.*
import javaposse.jobdsl.dsl.Job

public class JobBuilder {

    private DslFactory dslFactory
    /* default */ def job

    /**
     * Create job
     *
     * @String name   job name
     * @String type   job type (freestyle, pipeline)
     */
    public JobBuilder(DslFactory dslFactory, String name, String type = 'freestyle') {
        this.dslFactory = dslFactory;
        if ( type == 'freestyle' ) {
            this.job = this.dslFactory.job(name)
        } else if ( type == 'pipeline' ) {
            this.job = this.dslFactory.workflowJob(name)          
        } else {
            throw new Exception('Not supported job type')
        }
    }

    /**
     * Use this function to add shell step to the job configuration
     *
     * @String command  Add shell command
     */
    public JobBuilder addShellStep(String command) {
        job.steps() {
            shell(command)
        }
        this
    }

    /**
     * Use this function to add shell step to the job configuration
     *
     * @String command  Add shell command
     */
    public JobBuilder addShellScript(String filepath) {
        job.steps() {
            shell(dslFactory.readFileFromWorkspace(filepath))
        }
        this
    }


    /**
     * Use this function to add	pipeline script	definition as a file
     *
     * @String  script	Script to add
     */
    public JobBuilder addPipelineDefinitionFile(String filePath) {
        job.definition {
           cps { script(dslFactory.readFileFromWorkspace(filePath))
                 sandbox() }
        }
        this
    }

    /**
     * Use this function to add more default parameters to the job configuration
     *
     * @int num   number build to keep
     */
    public JobBuilder addLogRotator(int num = 20) {
        job.logRotator {
            numToKeep(num)
        }
        this
    }

    /**
     * Use this function to configure SCM block to job configuration
     *
     * @String repo         URL to Gitlab project
     * @String credential   Credentials for gitlab access
     * @String repoBranch   Specify branche
     */
    public JobBuilder addScmBlock(String repo, String repoBranch, String credential) {
        job.scm {
            git {
                remote {
                    name('origin')
                    url(repo)
                    credentials(credential)
                }
                branch(repoBranch)
            }
        }
        this
    }

    /**
     * Use this function to add SCM poll trigger
     *
     * @String schedule     Specify how often to poll - cron string
     * @boolean debug       debug mode
     */
    public JobBuilder addScmPollTrigger(String schedule = '* * * * *', boolean debug = false) {
        if (debug) {
            // Disable trigger
            println 'Debug is set to true'
        } else {
            job.triggers {
                scm(schedule)
            }
        }
        this
    }

    /**
     * Use this function to add GitLab trigger to the job configuration
     *
     * @param job Job instance
     * @boolean debug   debug mode
     */
    public JobBuilder addGitLabTrigger(boolean debug = false) {
        if (debug) {
            // Disable trigger
            println 'Debug is set to true'
        } else {
            job.triggers {
                gitlabPush {
                    buildOnMergeRequestEvents(true)
                    buildOnPushEvents(true)
                    enableCiSkip(false)
                    setBuildDescription(true)
                    addNoteOnMergeRequest(false)
                    rebuildOpenMergeRequest('never')
                    addVoteOnMergeRequest(false)
                    useCiFeatures(false)
                    acceptMergeRequestOnSuccess()
                    allowAllBranches(false)
                    includeBranches('')
                    excludeBranches('')
                }
            }
        }
        this
    }

    /**
     * Use this function to configure Slack block to the job configuration
     *
     * @String domain     Slack domain name
     * @String channel    Slack channel
     * @boolean debug      debug mode
     */
    public JobBuilder addSlackNotification(String domain, String channel) {
        job.publishers {
            slackNotifications {
                teamDomain domain
                projectChannel channel
                notifyFailure true
                notifySuccess true
                notifyUnstable true
                notifyBackToNormal true
            }
        }
        this
    }

    /**
     * Use this function to configure Pretested Integration plugin
     *
     * @boolean debug       debug mode
     */
    public JobBuilder addPretestedIntegration(String branchName = "master", boolean debug = false) {
        if (debug) {
            // Disable in debug mode
            println 'Debug is set to true'
        } else {
            job.configure { project ->
                project / publishers << 'org.jenkinsci.plugins.pretestedintegration.PretestedIntegrationPostCheckout' {}
                project / buildWrappers << 'org.jenkinsci.plugins.pretestedintegration.PretestedIntegrationBuildWrapper' {
                    scmBridge('class': 'org.jenkinsci.plugins.pretestedintegration.scm.git.GitBridge') {
                        branch branchName
                        integrationStrategy('class': 'org.jenkinsci.plugins.pretestedintegration.scm.git.SquashCommitStrategy')
                        repoName 'origin'
                    }
                    rollbackEnabled false
                }
            }
        }
        this
    }

    /**
     * Use this function to add delivery pipeline configuration
     *
     * @String buildStage
     * @String stepName
     */
    public JobBuilder addDeliveryPipelineConfiguration(String buildStage, String stepName) {
        job.deliveryPipelineConfiguration(buildStage, stepName)
        this
    }

    /**
     * Use this function to add delivery pipeline trigger
     *
     * @ArrayList jobs
     * @boolean debug  debug mode
     */
    public JobBuilder addDeliveryPipelineTrigger(ArrayList<String> jobs, boolean debug = false) {
        if (debug) {
            // Disable in debug mode
            println 'Debug is set to true'
        } else {
            job.publishers {
                buildPipelineTrigger(jobs.join(", "))
            }
        }
        this
    }

    /**
     *
     * @return ?
     */
    public Job build() {
        job
    }
}
