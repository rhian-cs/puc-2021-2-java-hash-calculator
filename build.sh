APP_NAME="HashCalculator"
MANIFEST_PATH="../META-INF/MANIFEST.MF"

# Delete previous build
rm -r ./build &> /dev/null || true

# Compile everything into bytecode
# JAVA_SRC_FILES=`find . -name "*.java"`
JAVA_SRC_FILES="Main.java $(find ./gui -name \"*.java\") ./hash_calculators/FileHashCalculator.java"
javac -d ./build $JAVA_SRC_FILES || exit 1

# Create an executable jar file
cd build
CLASS_FILES=`find . -name "*.class"`
jar cvfm "$APP_NAME.jar" "$MANIFEST_PATH" $CLASS_FILES || exit 2
chmod +x "$APP_NAME.jar" || exit 3
