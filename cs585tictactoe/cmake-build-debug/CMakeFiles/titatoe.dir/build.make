# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.8

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
CMAKE_COMMAND = /home/ericho/clion-2017.1.3/bin/cmake/bin/cmake

# The command to remove a file.
RM = /home/ericho/clion-2017.1.3/bin/cmake/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/ericho/CLionProjects/titatoe

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/ericho/CLionProjects/titatoe/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/titatoe.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/titatoe.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/titatoe.dir/flags.make

CMakeFiles/titatoe.dir/main.c.o: CMakeFiles/titatoe.dir/flags.make
CMakeFiles/titatoe.dir/main.c.o: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/ericho/CLionProjects/titatoe/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/titatoe.dir/main.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/titatoe.dir/main.c.o   -c /home/ericho/CLionProjects/titatoe/main.c

CMakeFiles/titatoe.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/titatoe.dir/main.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/ericho/CLionProjects/titatoe/main.c > CMakeFiles/titatoe.dir/main.c.i

CMakeFiles/titatoe.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/titatoe.dir/main.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/ericho/CLionProjects/titatoe/main.c -o CMakeFiles/titatoe.dir/main.c.s

CMakeFiles/titatoe.dir/main.c.o.requires:

.PHONY : CMakeFiles/titatoe.dir/main.c.o.requires

CMakeFiles/titatoe.dir/main.c.o.provides: CMakeFiles/titatoe.dir/main.c.o.requires
	$(MAKE) -f CMakeFiles/titatoe.dir/build.make CMakeFiles/titatoe.dir/main.c.o.provides.build
.PHONY : CMakeFiles/titatoe.dir/main.c.o.provides

CMakeFiles/titatoe.dir/main.c.o.provides.build: CMakeFiles/titatoe.dir/main.c.o


# Object files for target titatoe
titatoe_OBJECTS = \
"CMakeFiles/titatoe.dir/main.c.o"

# External object files for target titatoe
titatoe_EXTERNAL_OBJECTS =

titatoe: CMakeFiles/titatoe.dir/main.c.o
titatoe: CMakeFiles/titatoe.dir/build.make
titatoe: CMakeFiles/titatoe.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/ericho/CLionProjects/titatoe/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable titatoe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/titatoe.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/titatoe.dir/build: titatoe

.PHONY : CMakeFiles/titatoe.dir/build

CMakeFiles/titatoe.dir/requires: CMakeFiles/titatoe.dir/main.c.o.requires

.PHONY : CMakeFiles/titatoe.dir/requires

CMakeFiles/titatoe.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/titatoe.dir/cmake_clean.cmake
.PHONY : CMakeFiles/titatoe.dir/clean

CMakeFiles/titatoe.dir/depend:
	cd /home/ericho/CLionProjects/titatoe/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/ericho/CLionProjects/titatoe /home/ericho/CLionProjects/titatoe /home/ericho/CLionProjects/titatoe/cmake-build-debug /home/ericho/CLionProjects/titatoe/cmake-build-debug /home/ericho/CLionProjects/titatoe/cmake-build-debug/CMakeFiles/titatoe.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/titatoe.dir/depend

