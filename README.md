---
maintainer: 
 - andrey9kin
 - alexsedova
---
### Intro

Set of classes intented to simplify creation of readable and compact JobDSL scripts.
For the real life usage examples check out [Jenkins as code reference project](https://github.com/Praqma/JenkinsAsCodeReference)
Design inspired by Camilo Ribeiro and his [CDeasy](https://github.com/camiloribeiro/cdeasy) project

### Example

```
import net.praqma.jobdsl.helpers.JobBuilder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job

Job review = new JobBuilder(this as DslFactory, "jobdsl-helpers-review")
    .addLogRotator()
    .addShellStep('./gradlew test')
    .addShellStep('./gradlew archive')
    .addScmBlock('https://github.com/Praqma/jobdsl-helpers', "*/ready/**", 'jenkins')
    .addScmPollTrigger()
    .addPretestedIntegration('master')
    .build()

new JobBuilder(this as DslFactory, "jobdsl-helpers-pipeline", "pipeline")
    .addLogRotator()
    .addScmPollTrigger("@midnight")
    .addPipelineDefinitionFile("src/jobs/resources/pipelines/jobdsl-helpers-pipeline.groovy")
    .build()
```

### Contribute

To contribute push your changes to the ready/yourtext branch - Travis CI will build it and, if build passes, merge it to master.
Make sure rebase on top of the latest changes - only fast forward changes accepted.

Definition of Done:
- Issue referenced in the commit message
- Commit message describes what was done
- Commit is signed
- Tests
- Documentation

```
fix fix fix
git add <files>
git commit -s
git fetch
git rebase origin/master
git push origin HEAD:ready/<issue number or whatever you want>
```

### Links

* [Project builds in Travis CI](https://travis-ci.org/Praqma/jobdsl-helpers)
