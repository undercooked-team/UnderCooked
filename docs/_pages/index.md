---
layout: single
permalink: /
title: false
github: https://github.com/
download: https://github.com/undercooked-team/UnderCooked/releases/tag/game
author_profile: false
---
# Home Page
Welcome to our homepage for UnderCooked!

{{ site.description }}

All the contents for this website are on this page, the navigation bar in the header will take you to a specified heading on this page.

## Download UnderCooked!
> [{{page.download}}]({{page.download}})

## Our GitHub Repository
Below is a link to the GitHub Repository we've been developing our game in:

> [{{ page.github }}{{ site.repository }}]({{ page.github }}{{ site.repository }})

## The Team
Details for all the team members can be found here:
<ul>
<li>Fin Cochrane   - ftc505@york.ac.uk</li>
<li>Sehran Ahmed   - rsa533@york.ac.uk</li>
<li>Sam Davis      - sd1598@york.ac.uk</li>
<li>Hamza Salman   - hs1955@york.ac.uk</li>
<li>Owen Thomas    - obt503@york.ac.uk</li>
<li>Zhenyi Xu      - zx1090@york.ac.uk</li>
</ul>

## Requirements:
[Requirements Document](https://docs.google.com/document/d/17tcN_Xeo0Gzz8ficlVVp_1x0h8Q2EDLzrCXvKtgHNlw/edit?usp=share_link)

## Architecture:
Below are UML images showcasing our development process for the Final Architecture Design (for Assessment 1).

### Use-case Diagram:
The below summarises the general outcome of this project:
![Use-case Diagram](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/UseCaseDiagramUnderCooked.png)

### RDD Table:
We initially designed a Responsibility-Driven Design Table (RDD) out of our requirements:
![RDD Table](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/RDD.png)

Using the RDD above, we made our Initial UML Diagram.
### Initial UML Diagram:
![Initial UML Diagram](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/InitialUML.png)

Over time, we expanded upon the Initial UML diagram, making changes where necessary:
### Addition 1: Food
![Food UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/food.png)

- **FoodStack** is responsible for holding a stack of in-game cooking ingredients. The chef class will use this object to hold food items.
- **FoodItem** contains information about how to render each ingredient.
- **Recipe** contains each recipe and how each one is made. We learnt that to consrtuct salad and burgers, the cook must hold a certain **FoodStack**. Thus, certain **FoodStack**s correlate to certain recipes. **Recipe** holds a dict which translates a String recipeName to Array<String> listOfFoodStacks which are all the foodStacks which correlate to recipe recipeName.

### Addition 2: Cooks
![Cooks UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/cooks.png)

- **Chef** was renamed to **Cook**.
- **Collidable** branched into the **GameEntity** class, which was used as a base class for any item that could exist as an object with a physical position in the game-world. We learnt that classes **Cook** and **Customer** can use the same code in **GameEntity**.
- We made **CookInteractor** because this object is solely responsible for letting a cook interact with other objects. Any other object within the **CookInteractor** Rectangle, is an object that the **Cook** and interact with.
  

### Addition 3: Stations
![Stations UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/stations.png)

- **CookInteractable** allowed a station to become interactable with the cook.
- We quickly realised that we would need more stations to allow the player to have greater ability to manipulate the stack of items that they will hold. For example, if the player accidentally collects 1 too many items, they need to be able to bin the top item (**BinStation**). Or if the player needs to swap items with the other cook, they need to be able to place their FoodStack down (**CounterStation**).

## Method Selection and Planning
[Method Selection and Planning Doc](https://docs.google.com/document/d/1KpzhVRxdkBJPyYyQxW3aTuWMCYpDrK8jtEIiyoh-2Rw/edit?usp=share_link)

## Risk Assessment and Mitigation
[Risk Assessment and Mitigation Doc](https://docs.google.com/document/d/1-oQ4ajU6W9XvK9kMU9fiHc9EGryhhc9Awz3sKuWzotQ/edit?usp=share_link)

## Implementation
[Implementation Doc](https://docs.google.com/document/d/1rC9LAZ9OATbIcQgagMG_clQ7LpItRECm9ADfuPLFHQc/edit?usp=share_link)
