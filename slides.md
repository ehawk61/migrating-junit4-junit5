---
marp: true
theme: uncover 
class: 
 - invert
 - lead
paginate: true
_paginate: false
footer: Copyright 2021 Jonathan L. Meek 
---

# Making the Jump
## Migrating From JUnit 4 to 5
 
---
# Who Is Jonathan L. Meek? 
- An active Java Developer since 2010 
- Oldest Version of Java JDK worked with: 1.6 
- Training for 1st Half Marathon 
- Has two cats: Deacon and Rayna 
- Married to best Agile Coach/Baker known to me
---
# Problem At Hand 
### We talk about JDKs and coding frameworks but not **testing** frameworks
---
# Some Thoughts on the Problem 
- JUnit4 was first released in 2006 
- We aren't putting forth presentaions or talking about 
---
#### It's just a dependency swapout, right? 
- Nope 
  - The project is re-packaged into three packages: 
    - JUnit Platform – Contains the TestEngine API interfaces ​
    - JUnit Jupiter – Contains the TestEngine API implementation​
    - JUnit Vintage – Supports running JUnit 3 & 4 tests in JUnit 5 
---
### Couldn't I Just Use JUnit Vintage? 
<Insert funny meme here>

---
### TL:DR on the annotation diff
|Feature | JUnit 4| JUnit 5 |
|--|---| --|
|Declare a test method​ |@Test |​@Test |​
|Execute before all test methods in the current class​ | @BeforeClass​ |@BeforeAll​|
|Execute after all test methods in the current class​ | @AfterClass​ |@AfterAll |​ 
---

|Feature | JUnit 4| JUnit 5 |
|--|---| --|
|Execute before each test method​| @Before​| @BeforeEach​ |
| Execute after each test method​| @After​ |@AfterEach​
|Disable a test method / class​ |@Ignore​|@Disabled​

Register custom extensions​

@RunsWith​

@ExtendWith​


---
### TL;DR on the package diff

---
#### Pre-reqs to Making the Jump
- Run Java 8 or higher and Spring Framework or higher
    - Spring Boot 2.0.8 or higher (Spring Boot 2.4 set JUnit 5 as a default)
- Upgrade the dependencies in the pom.xml
---
#### Making the Jump 
- Swap out with the following bits: 
    - `@RunWith` -> `@ExtendWith`
    - `Spring*Runner.class` -> `SpringExtensions.class`​
    - `@Before` -> `@BeforeEach`​
    - `MockitoJUnitRunner.class` -> `MockitoExtension.class​`
- See TL:DR diff on the package diff for package guidance
    