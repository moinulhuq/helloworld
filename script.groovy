def buildApp() {
	echo "Hello World"
}

def testApp() {
	sh 'mvn --version'
}

def deployApp() {
	echo 'version ${params.VERSION}'
}

return this
