# Download size check service

Check html size (at this moment work only with pages who return header "Content-Length")

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installing

1. You need install [Maven](https://maven.apache.org/)

2. Clone repository 

```
git clone <repository-path>
```

3. Run Maven command

```
mvn clean install
```

## Example

1. Request for a single HTML size

```
GET: http://localhost:8080/resources/size?url=<resource_url>
```

format *<resource_url>*

```
https://www.google.com
```

Response example

```
{
    "totalSize": 258700,
    "requestCount": 3,
    "externalResource": {
        "https://www.google.com/tia/tia.png": 258,
        "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png": 13504
    }
}
```

**'total Size'** is the total size with all external links.

**'requestCount'** is the total number of request executed to receive data.

**'externalResource'** are all images on the current page and their size


## Authors

* **Alexander Kurilo**  - [GitHun](hhttps://github.com/AleksKurilo)




