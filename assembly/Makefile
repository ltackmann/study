SOURCES = $(wildcard *.s)
EXECUTABLES = $(SOURCES:.s=)
OBJECTS = $(SOURCES:.s=.o)

# Generic rule
# $< is the first dependency name
# $@ is the target filename
%.o: %.s
	as $< -o $@

default: $(OBJECTS)
	for e in $(EXECUTABLES) ; do \
		ld -lc -macosx_version_min 10.8.5 $$e.o -o $$e.exe ; \
	done

.PHONY: clean
clean:
	rm *.o
	rm *.exe
