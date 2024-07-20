
MAIN_CLASS :=  com.danske.taxmanager.TaxManagerApplication
IMAGE_NAME := tax-manager
DOCKERFILE := Dockerfile
JAR_FILE := target/tax-manager-0.0.1.jar
CONTAINER_NAME := tax_manager_springboot_app
MARIADB_CONTAINER_NAME := mariadb_container
MARIADB_IMAGE := mariadb:latest

# default profile H2
PROFILE ?= h2


clean:
	mvn clean

build:
	mvn install

run:
	mvn spring-boot:run -Dspring-boot.run.profiles=$(PROFILE)

docker-build:
	docker build -t $(IMAGE_NAME) -f $(DOCKERFILE) .


# running docker image directly 
# specify a profile, by default h2, if using mariadb make sure mariadb is running

docker-run:
	docker run -d -p 8080:8080 \
		-e SPRING_PROFILES_ACTIVE=$(PROFILE) \
	 	--name $(CONTAINER_NAME) $(IMAGE_NAME)

docker-clean:
	docker stop $(CONTAINER_NAME) || true
	docker rm $(CONTAINER_NAME) || true
	docker stop $(MARIADB_CONTAINER_NAME) || true
	docker rm $(MARIADB_CONTAINER_NAME) || true


# run with both docker mariadb (non persistent db and will start a new mariadb container)
docker-compose:
	docker compose up

start-mariadb:
	docker pull $(MARIADB_IMAGE)
	docker run -d \
		--name $(MARIADB_CONTAINER_NAME) \
		-e MYSQL_ROOT_PASSWORD=root \
		-e MYSQL_DATABASE=taxmanager \
		-p 3306:3306 \
		$(MARIADB_IMAGE)


