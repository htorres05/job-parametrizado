job('job-dsl-ejemplo2') {
  	description('Job DSL necesario para el curso de Jenkins')
  scm {
    git('https://github.com/htorres05/job-parametrizado.git', 'main') { node ->
      node / gitConfigName('htorres05')
      node / gitConfigEmail('hugo.torres@grupocarsa.com')
    }
  }
  parameters {
  	stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para le job')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
    githubPush()
  }
  steps {
    shell("bash jobscript.sh") 
  }
  publishers {
    mailer('hugo.torres@grupocarsa.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }  
  }
}
