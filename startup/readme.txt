smal2.sh
    /etc/init.d script to run at system boot

startup_loop.sh
    script to infinite run the program
    usage:
        bash -x startup_loop.sh /srv/local/Smal2 /usr/bin/mvn "tomcat:run -Dmaven.repo.local=/srv/local/Smal2/.m2" start
    OR
        nohup ./startup_loop.sh /srv/local/Smal2 /usr/bin/mvn "tomcat:run -Dmaven.repo.local=/srv/local/Smal2/.m2" start &
