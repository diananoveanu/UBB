# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.15

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = "/Users/diananoveanu/Library/Application Support/JetBrains/Toolbox/apps/CLion/ch-0/193.6015.37/CLion.app/Contents/bin/cmake/mac/bin/cmake"

# The command to remove a file.
RM = "/Users/diananoveanu/Library/Application Support/JetBrains/Toolbox/apps/CLion/ch-0/193.6015.37/CLion.app/Contents/bin/cmake/mac/bin/cmake" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/hamiltonean.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/hamiltonean.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/hamiltonean.dir/flags.make

CMakeFiles/hamiltonean.dir/hamiltonean.cpp.o: CMakeFiles/hamiltonean.dir/flags.make
CMakeFiles/hamiltonean.dir/hamiltonean.cpp.o: ../hamiltonean.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/hamiltonean.dir/hamiltonean.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/hamiltonean.dir/hamiltonean.cpp.o -c /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/hamiltonean.cpp

CMakeFiles/hamiltonean.dir/hamiltonean.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/hamiltonean.dir/hamiltonean.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/hamiltonean.cpp > CMakeFiles/hamiltonean.dir/hamiltonean.cpp.i

CMakeFiles/hamiltonean.dir/hamiltonean.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/hamiltonean.dir/hamiltonean.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/hamiltonean.cpp -o CMakeFiles/hamiltonean.dir/hamiltonean.cpp.s

# Object files for target hamiltonean
hamiltonean_OBJECTS = \
"CMakeFiles/hamiltonean.dir/hamiltonean.cpp.o"

# External object files for target hamiltonean
hamiltonean_EXTERNAL_OBJECTS =

hamiltonean: CMakeFiles/hamiltonean.dir/hamiltonean.cpp.o
hamiltonean: CMakeFiles/hamiltonean.dir/build.make
hamiltonean: CMakeFiles/hamiltonean.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable hamiltonean"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/hamiltonean.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/hamiltonean.dir/build: hamiltonean

.PHONY : CMakeFiles/hamiltonean.dir/build

CMakeFiles/hamiltonean.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/hamiltonean.dir/cmake_clean.cmake
.PHONY : CMakeFiles/hamiltonean.dir/clean

CMakeFiles/hamiltonean.dir/depend:
	cd /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/cmake-build-debug /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/cmake-build-debug /Users/diananoveanu/UBB/Sem5/PDP/Exam/hamiltonean/cmake-build-debug/CMakeFiles/hamiltonean.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/hamiltonean.dir/depend

