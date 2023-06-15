# AzuraStack
___
This project is a collection of API's made for SpigotMC to assist developers in doing tasks that are usually seen as repetitive or overly complicated for what they achieve.

# Coding Guidelines
___
### Principles
- SOLID
- KISS
- DRY

### Naming Conventions
- Class, Field, Variable Name
- Class Names in UpperCamelCase (Nouns)
- Method Names in lowerCamelCase (Verbs)

### General Guidelines
- If you break something you must fix it.
- Do not use the world Manager (Substitute it with another word).
- Classes should do **ONE** thing and **ONE** thing **WELL**.
  - Methods are things that help you achieve a goal.
- The most important function of computer code is to communicate the programmers intent to a human reader.
  - for(p = a; p < lim; p++) - This is bad code that is difficult to read.
- No more than **TWO** nested blocks per method (Use guarded statements).
- Try to write code **WITHOUT** a utility class.
- Avoid premature optimisation:
  - 90% of your optimisations should occur at the design stage.
  - If you haven't profiled the code. It's premature!
- Do not **CHAIN** stuff that doesn't need to be chained.
- Tabs or Spaces.
- Blank line before the first member of the class **(REQUIRED)**.
- Treating method args as local variables **(FINAL)**.

### Dependency Guidelines
- Do not rely on dependencies that are not **WELL KNOWN, MAINTAINED, OR DOCUMENTED**.

### Async Programming Guidelines
- Immutable Classes **ONLY**.
- Only run thread-safe operations.

### Logging
- Logging is required.

### Exception Handling
- Caught exceptions should not be ignored.
- Try not to default to Exception, use the actual exception relevant to it.