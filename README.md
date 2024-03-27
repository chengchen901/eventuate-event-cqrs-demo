sudo ifconfig lo0 alias 10.200.10.1/24  # (where 10.200.10.1 is some unused IP address)

export DOCKER_HOST_IP=10.200.10.1

export EVENTUATE_COMMON_VERSION=0.18.0.RELEASE
export EVENTUATE_MESSAGING_KAFKA_IMAGE_VERSION=0.18.0.RELEASE
export EVENTUATE_CDC_VERSION=0.16.0.RELEASE


docker-compose -f docker-compose-eventuate-local-mysql.yml up -d

docker-compose -f docker-compose-eventuate-local-mysql.yml down -v