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
CMAKE_SOURCE_DIR = /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/quicksort.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/quicksort.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/quicksort.dir/flags.make

CMakeFiles/quicksort.dir/threads.cpp.o: CMakeFiles/quicksort.dir/flags.make
CMakeFiles/quicksort.dir/threads.cpp.o: ../threads.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/quicksort.dir/threads.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/quicksort.dir/threads.cpp.o -c /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/threads.cpp

CMakeFiles/quicksort.dir/threads.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/quicksort.dir/threads.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/threads.cpp > CMakeFiles/quicksort.dir/threads.cpp.i

CMakeFiles/quicksort.dir/threads.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/quicksort.dir/threads.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/threads.cpp -o CMakeFiles/quicksort.dir/threads.cpp.s

# Object files for target quicksort
quicksort_OBJECTS = \
"CMakeFiles/quicksort.dir/threads.cpp.o"

# External object files for target quicksort
quicksort_EXTERNAL_OBJECTS =

quicksort: CMakeFiles/quicksort.dir/threads.cpp.o
quicksort: CMakeFiles/quicksort.dir/build.make
quicksort: CMakeFiles/quicksort.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable quicksort"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/quicksort.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/quicksort.dir/build: quicksort

.PHONY : CMakeFiles/quicksort.dir/build

CMakeFiles/quicksort.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/quicksort.dir/cmake_clean.cmake
.PHONY : CMakeFiles/quicksort.dir/clean

CMakeFiles/quicksort.dir/depend:
	cd /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/cmake-build-debug /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/cmake-build-debug /Users/diananoveanu/UBB/Sem5/PDP/Exam/quicksort/cmake-build-debug/CMakeFiles/quicksort.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/quicksort.dir/depend

