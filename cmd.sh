#!/bin/bash
LAST=$(stat -c%Y cmd.sh)
cd bin
echo ""
FILES=""
for file in ../src/sevenWonders/backend/*.java;
do
#	TIME=$(stat -c%Y $file)
#	if [ $LAST -lt $TIME ]
#	then
	FILES="$FILES $file"
#	fi
done
for file in ../src/sevenWonders/frontend/*.java;
do
#	TIME=$(stat -c%Y $file)
#	if [ $LAST -lt $TIME ]
#	then
	FILES="$FILES $file"
#	fi
done
for file in ../src/sevenWonders/*.java;
do
#	TIME=$(stat -c%Y $file)
#	if [ $LAST -lt $TIME ]
#	then
	FILES="$FILES $file"
#	fi
done
echo "Files: $FILES"
$(javac $FILES)

mv ../src/sevenWonders/*.class sevenWonders/
mv ../src/sevenWonders/frontend/*.class sevenWonders/frontend/
mv ../src/sevenWonders/backend/*.class sevenWonders/backend/
cd ..
touch cmd.sh
echo ""
java -cp bin/ sevenWonders.frontend.Main

