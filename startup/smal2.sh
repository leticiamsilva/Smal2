#!/bin/sh
#
# smal2.sh

PATH=/sbin:/bin:/usr/sbin:/usr/bin;

USER="nobody";
CMD_PATH="/srv/local/Smal2";
CMD_BIN="startup_loop.sh";
CMD_ARGS="${CMD_PATH} /usr/bin/mvn \"tomcat:run -Dmaven.repo.local=${CMD_PATH}/.m2\""



case "$1" in
    start)
        su ${USER} -c "cd ${CMD_PATH}; ./${CMD_BIN} ${CMD_ARGS} start &";
        RET_CODE=$?;
        ;;
    stop)
        su ${USER} -c "cd ${CMD_PATH}; ./${CMD_BIN} ${CMD_ARGS} stop &";
        RET_CODE=$?;
        ;;
    status|restart|reload|force-reload)
        echo "Error: argument '$1' not supported" >&2;
        RET_CODE=1;
        ;;
    *)
        echo "Usage: $0 {start|stop}";
        RET_CODE=1;
        ;;
esac;

exit "${RET_CODE}";



# End
