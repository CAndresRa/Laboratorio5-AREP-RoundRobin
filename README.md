# Laboratorio05 AREP
### Autor: Carlos Andrés Ramírez Torres

## Creación imagen RoundRobinApp

#### Demo

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/video.gif)

#### Dockerfile

* Se crea el archivo dockerfile para la creación de la imagen mediante el mismo.

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/dockerfile%20robin.png)

#### Build image docker, push a repositorio en docker hub

* Mediante el siguiente comando se crea una imagen del programa 

```docker build --tag <Nombre del repositorio en dockerhub>```

* Se ejecuto de la siguiente manera.

``` docker build --tag candresra/roundrobin . ```

* Adicionalmente se muestra el push al repositorio en docker hub con el nombre *candresra/roundrobin* de la siguiente forma.

``` docker push candresra/roundrobin ```

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/Creando%20imagen%20robin.png)

* Link de la imagen: [candresra/roundrobin](https://hub.docker.com/repository/docker/candresra/roundrobin)

## Creación imagen ServiceLog

* Link del repositorio del servicio [Laboratorio5 AREP ServiceLog](https://github.com/CAndresRa/Laboratorio5-AREP-ServiceLog)

#### Dockerfile

* Se crea el archivo dockerfile para la creación de la imagen mediante el mismo.

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/dockerfile%20service.png)

#### Build image docker, push a repositorio en docker hub

* Mediante el siguiente comando se crea una imagen del programa 

```docker build --tag <Nombre del repositorio en dockerhub>```

* Se ejecuto de la siguiente manera.

``` docker build --tag candresra/logserviceone . ```

* Adicionalmente se muestra el push al repositorio en docker hub con el nombre *candresra/logserviceone* de la siguiente forma.

``` docker push candresra/logserviceone ```

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/creando%20imagen%20service.png)

* Link de la imagen: [candresra/logserviceone](https://hub.docker.com/repository/docker/candresra/logserviceone)


## Desplegando en AWS

* Una vez creada la instancia Ubuntu en AWS se procede a instalar **Docker** mediante los siguientes comandos.

```
sudo yum update -y

sudo yum install docker

sudo service docker start

sudo usermod -a -G docker ec2-user
```

## Colocar imagenes dentro de la instancia AWS

* Se debe ejecutar el siguiente comando:

``` docker run -d -p <public port> : <local port> --name <Nombre container> <Imagen>```

#### RoundRobin aws

``` docker run -d -p 8080:8080 --name roundrobin candresra/roundrobin```

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/Poniendo%20imagen%20robin%20aws.png)

#### ServiceLog

``` docker run -d -p 35000:35000 --name servicelogone candresra/servicelogone```

``` docker run -d -p 35001:35000 --name servicelogtwo candresra/servicelogone```

``` docker run -d -p 35002:35000 --name servicelogtres candresra/servicelogone```

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/poniendo%20imagen%20log%20en%20aws.png)

#### Mongodb AWS

``` docker run -d -p 27017:27017 --name database mongo```

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/poniendo%20mongo%20aws.png)

#### Servicios run

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/Todos%20los%20servicios%20corriendo.png)

#### Imagenes en AWS

![](https://github.com/CAndresRa/RoundRobinApp/blob/master/ImagenesMicroservices/las%20imagenes%20en%20aws.png)




