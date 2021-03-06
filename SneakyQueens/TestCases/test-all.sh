#!/bin/bash

# Sean Szumlanski
# COP 3503, Spring 2019

# =========================
# SneakyQueens: test-all.sh
# =========================
# You can run this script at the command line like so:
#
#   bash test-all.sh
#
# For more details, see the assignment PDF.


################################################################################
# Shell check.
################################################################################

# Running this script with sh instead of bash can lead to false positives on the
# test cases. Yikes! These checks ensure the script is not being run through the
# Bourne shell (or any shell other than bash).

if [ "$BASH" != "/bin/bash" ]; then
  echo ""
  echo " Bloop! Please use bash to run this script, like so: bash test-all.sh"
  echo ""
  exit
fi

if [ -z "$BASH_VERSION" ]; then
  echo ""
  echo " Bloop! Please use bash to run this script, like so: bash test-all.sh"
  echo ""
  exit
fi


################################################################################
# Initialization.
################################################################################

PASS_CNT=0
NUM_TEST_CASES=6


################################################################################
# Check that all required files are present.
################################################################################

if [ ! -f SneakyQueens.java ]; then
	echo ""
	echo " Error: You must place SneakyQueens.java in this directory before we can"
	echo "        proceed. Aborting test script."
	echo ""
	exit
elif [ ! -d sample_output ]; then
	echo ""
	echo " Error: You must place the sample_output folder in this directory"
	echo "        before we can proceed. Aborting test script."
	echo ""
	exit
elif [ ! -d input_files ]; then
	echo ""
	echo " Error: You must place the input_files folder in this directory"
	echo "        before we can proceed. Aborting test script."
	echo ""
	exit
fi

for i in `seq -f "%02g" 1 $NUM_TEST_CASES`;
do
	if [ ! -f TestCase$i.java ]; then
		echo ""
		echo " Error: You must place TestCase$i.java in this directory before we"
		echo "        can proceed. Aborting test script."
		echo ""
		exit
	fi
done

for i in `seq -f "%02g" 1 $NUM_TEST_CASES`;
do
	if [ ! -f sample_output/TestCase$i-output.txt ]; then
		echo ""
		echo " Error: You must place TestCase$i-output.txt in the sample_output directory"
		echo "        before we can proceed. Aborting test script."
		echo ""
		exit
	fi
done

for i in `seq -f "%02g" 3 $NUM_TEST_CASES`;
do
	if [ ! -f input_files/TestCase$i-input.txt ]; then
		echo ""
		echo " Error: You must place TestCase$i-input.txt in the input_files directory"
		echo "        before we can proceed. Aborting test script."
		echo ""
		exit
	fi
done


################################################################################
# Run test cases with input specified at command line (standard test cases).
################################################################################

echo ""
echo "================================================================"
echo "Running test cases..."
echo "================================================================"
echo ""

for i in `seq 1 $NUM_TEST_CASES`;
do
	echo -n "  [Test Case] TestCase0$i ... "

	rm -rf *.class

	# Attempt to compile.
	javac SneakyQueens.java TestCase0$i.java 2> /dev/null
	compile_val=$?
	if [[ $compile_val != 0 ]]; then
		echo "fail (failed to compile)"
		continue
	fi

	# Run program. Capture return value to check whether it crashes.
	java TestCase0$i > myoutput.txt 2> /dev/null
	execution_val=$?
	if [[ $execution_val != 0 ]]; then
		echo "fail (program crashed)"
		continue
	fi

	# Run diff and capture its return value.
	diff myoutput.txt sample_output/TestCase0$i-output.txt > /dev/null
	diff_val=$?
	
	# Output results based on diff's return value.
	if  [[ $diff_val != 0 ]]; then
		echo "fail (output does not match)"
	else
		echo "PASS!"
		PASS_CNT=`expr $PASS_CNT + 1`
	fi
done


############################################################################
# Check for tabs vs. spaces.
############################################################################

echo ""
echo "================================================================"
echo "Checking for tabs vs. spaces..."
echo "================================================================"
echo ""

num_spc_lines=`grep -P "^ " SneakyQueens.java | wc -l`
num_tab_lines=`grep -P "^\t" SneakyQueens.java | wc -l`
num_und_lines=`grep -P "^[^\t ]" SneakyQueens.java | wc -l`

echo "  Number of lines beginning with spaces: $num_spc_lines"
echo "  Number of lines beginning with tabs: $num_tab_lines"
echo "  Number of lines with no indentation: $num_und_lines"

if [ "$num_spc_lines" -gt 0 ] && [ "$num_tab_lines" -gt 0 ]; then
	echo ""
	echo "  Whoa, buddy! It looks like you're starting some lines of code with"
	echo "  tabs, and other lines of code with spaces. You'll need to choose"
	echo "  one or the other."
	echo ""
	echo "  Try running the following commands to see which of your lines begin"
	echo "  with spaces (the first command below) and which ones begin with tabs"
	echo "  (the second command below):"
	echo ""
	echo "     grep -P \"^ \" SneakyQueens.java -n"
	echo "     grep -P \"^\t\" SneakyQueens.java -n"
elif [ "$num_spc_lines" -gt 0 ]; then
	echo ""
	echo "  Looks like you're using spaces for all your indentation! (Yay!)"
elif [ "$num_tab_lines" -gt 0 ]; then
	echo ""
	echo "  Looks like you're using tabs for all your indentation! (Yay!)"
else
	echo ""
	echo "  Whoa, buddy! It looks like none of your lines of code are indented!"
fi


################################################################################
# Cleanup phase.
################################################################################

# Clean up any lingering .class files.
rm -f *.class

# Clean up the output files generated by this script.
rm -f myoutput.txt


################################################################################
# Final thoughts.
################################################################################

echo ""
echo "================================================================"
echo "Final Report"
echo "================================================================"

if [ $PASS_CNT -eq $NUM_TEST_CASES ]; then
	echo ""
	echo "  CONGRATULATIONS! You appear to be passing all the test cases!"
	echo "  (Now, don't forget to create some extra test cases of your own."
	echo "  These test cases are not comprehensive.)"
	echo ""
else
	echo "                           ."
	echo "                          \":\""
	echo "                        ___:____     |\"\\/\"|"
	echo "                      ,'        \`.    \\  /"
	echo "                      |  o        \\___/  |"
	echo "                    ~^~^~^~^~^~^~^~^~^~^~^~^~"
	echo ""
	echo "                           (fail whale)"
	echo ""
	echo "Note: The fail whale is friendly and adorable! He is not here to"
	echo "      demoralize you, but rather, to bring you comfort and joy"
	echo "      in your time of need. \"Keep plugging away,\" he says! \"You"
	echo "      can do this!\""
	echo ""
fi
