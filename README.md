# API Framework Design

A comprehensive REST API testing framework built with Java, RestAssured, and TestNG.

## Overview

This framework provides a robust solution for API testing with features including:
- RESTful API testing using RestAssured
- Test management with TestNG
- Detailed logging and reporting
- Maven-based project structure
- Reusable utilities and helper classes

## Technologies Used

- **Java** - Programming language
- **RestAssured** - REST API testing library
- **TestNG** - Testing framework
- **Maven** - Build and dependency management
- **Log4j** - Logging framework

## Project Structure

```
DesignRestFramework/
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── api/
│                   ├── tests/          # Test classes
│                   ├── utils/          # Utility classes
│                   └── base/           # Base test setup
├── logs/                               # Application logs (gitignored)
├── test-output/                        # Test reports (gitignored)
├── target/                             # Maven build output (gitignored)
├── pom.xml                             # Maven configuration
└── testng.xml                          # TestNG suite configuration
```

## Prerequisites

- Java JDK 8 or higher
- Maven 3.6 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Setup

1. Clone the repository:
```bash
git clone https://github.com/SubhamOjha-commits/ApiFrameworkDesign.git
cd ApiFrameworkDesign
```

2. Install dependencies:
```bash
mvn clean install
```

## Running Tests

Execute all tests:
```bash
mvn test
```

Run specific test suite:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Reports

- **Test Reports**: Generated in `test-output/` directory
- **Logs**: Available in `logs/` directory

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Author

**Subham Ojha**

## License

This project is open source and available under the MIT License.

