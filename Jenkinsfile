pipeline{
    agent any
    
    environment{
        JENKINS_NODE_COOKIE = 'dontkillmeplease'
        PORT=9011
    }
     stages{
    	stage('Destroy Old Server'){
    	    steps{
    	        script{
    	            try{
    	                // kill any running instances
    	                sh 'kill $(lsof -t -i:$PORT)'
    	            } catch(all){
    	                // if it fails that means that a server wasn't running
    	                echo 'No server was running'
    	            }
    	        }
    	    }
    	}
    	stage('Install maven dependencies'){
    	    steps{
    	    	// clean install maven
				sh 'mvninstall'    	        
    	    }
    	}
    	stage('Clean & Package'){
    	    steps{
    	        sh 'mvn clean package'
    	    }
    	}
		// Tries to remove an existing docker container named re2
		stage('Remove Docker Container'){
		    steps{
		        try{
		            sh 'docker rm -f re2'
		        } catch(all){
		            echo 'Docker Container re2 does not exist'
		        }
		    }
		}
		// Delete images that have no container
		stage('Delete Docker Image'){
		    steps{
		        sh 'docker image prune -a -f'
		    }
		}
		stage('Docker Build'){
		    steps{
		        sh 'docker build -t bubbagump2020/re2-back:v1 '
		    }
		}
		stage('Docker Run'){
		    steps{
		        sh 'docker run -p 9011:9011 --name re2 -it -d bubbagump2020/re2-back:v1'
		    }
		}
		stage('Docker Log'){
		    steps{
		        sh 'nohup docker logs -f re2 > /home/ec2-user/.jenkins/workspace/RE2-Back/logs/application.log &';
		    }
		}
		stage('Docker Check Containers'){
		    steps{
		        sh 'docker ps -a'
		    }
		}
	}
}
