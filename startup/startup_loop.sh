#!/bin/bash
#
# NAME      startup_loop.sh
# REQUIRED  -
# VERSION   1.1
#


# input
WORK_PATH="$1";
COMMAND_PATH="$2";

if test -z "$4"; then
    COMMAND_ARGS="";
    ACTION="$3";
else
    COMMAND_ARGS="$3";
    ACTION="$4";
fi;

COMMAND_LOG="${WORK_PATH}/output.log";



if test -z "${ACTION}"; then
    echo "Usage: $0 <work_path> <command_path> [command_args] {start|stop}";
    exit 1;
fi;

if test ! -d "${WORK_PATH}"; then
    echo "<work_path> parameter required: exit.";
    exit 2;
fi;

if test ! -x "${COMMAND_PATH}"; then
    echo "${COMMAND_PATH} is not executable: exit.";
    exit 3;
fi;



echo "Action: ${ACTION}...";

if test "${ACTION}" = "start"; then
    ulimit -c 5000000;
    cd "${WORK_PATH}";

    while true; do
        echo >> "${COMMAND_LOG}";
        echo starting LD_LIBRARY_PATH=${WORK_PATH} ${COMMAND_PATH} ${COMMAND_ARGS} >> "${COMMAND_LOG}"
        LD_LIBRARY_PATH=${WORK_PATH} ${COMMAND_PATH} ${COMMAND_ARGS} >> "${COMMAND_LOG}" 2>&1
        sleep 10s;
    done;
fi;



# End
