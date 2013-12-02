import  groovyx.net.http.*;

def SERVER = 'http://www.thecodelesscode.com/'

def OUTPUT_FOLDER = "C:/Users/ndx/Documents/workspaces/git/the-codeless-code/the-codeless-code/en/"

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.2' )
def createHTTP(server) {
    return new HTTPBuilder(server)
}

def download(HTTPBuilder http, String url, String path) {
    def destination = new File(path)
    if(!destination.exists()) {
        try {
            def text = http.get(path:url)
            destination.withWriter("UTF-8") { writer ->
                writer << text
            }
            System.out.println "written "+path
        } catch(Exception e) {
            System.err.println "NOT written "+path
        }
    }
}

def http = createHTTP(SERVER)

for(i in 1..121) {
    def sourcePath = "/cases/"+i+"/raw.txt"
    def destination = OUTPUT_FOLDER+"case-"+i+".txt"
    download(http, sourcePath, destination)
}