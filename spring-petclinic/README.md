# Spring PetClinic

This folder holds a local clone of [Spring PetClinic](https://github.com/spring-projects/spring-petclinic). The first session demo points Claude Code at it and asks it to read the project.

The clone is not committed to this repo. Only this README is. Clone it fresh on each machine.

## Clone it

From the root of this repo:

```bash
git clone https://github.com/spring-projects/spring-petclinic.git app
```

The clone lands in `spring-petclinic/app`, not in this folder directly. PetClinic ships its own `README.md`, and cloning here would replace this one.

## Run it

The demo only reads the code, so no build is needed. If you want to see the app:

```bash
cd spring-petclinic/app
./mvnw spring-boot:run
```

Then open http://localhost:8080.
