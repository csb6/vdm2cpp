#!/usr/bin/env sh

# Note: requires LLVM's FileCheck tool to be installed and in the PATH.
# See: https://llvm.org/docs/CommandGuide/FileCheck.html

set -e

for testfile in src/test/*; do
  echo "Testing ${testfile}...";
  mvn exec:java -Dexec.args="-vdmsl ${testfile}" | FileCheck ${testfile};
  if [ $? -ne 0 ]; then
    break
  fi
done