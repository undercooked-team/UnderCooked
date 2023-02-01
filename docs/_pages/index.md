---
layout: single
permalink: /
title: false
github: https://github.com/
download: https://github.com/undercooked-team/UnderCooked/releases/tag/game
req_pdf: https://drive.google.com/file/d/1riehAdib1IffNDNwmmPX9ugbUv2AZZnf
arch_pdf: https://drive.google.com/file/d/1gGG0oPKWnQbTWOHgsPOo8c6Kgt7APBZx
plan_pdf: https://drive.google.com/file/d/1HipDr3_7fABNSKuUDhcKLNDbxVc3lMcK
risk_pdf: https://drive.google.com/file/d/1VSMtAjT8UPiVwPZLI06Vrd8EyYSrAjU8
impl_pdf: https://drive.google.com/file/d/1PoRtP0_H23nPD6mZUeKYy8YWXTVgU7Rm
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
[Requirements Google Doc](https://docs.google.com/document/d/17tcN_Xeo0Gzz8ficlVVp_1x0h8Q2EDLzrCXvKtgHNlw/edit?usp=share_link)

[Requirements PDF]({{page.req_pdf}})

## Architecture:
[Architecture Google Doc](https://docs.google.com/document/d/11IZyqiyvOG_iuKlP9LPBgSTnA8E4lxPNE8mAL1pA26s/edit?usp=sharing)

[Architecture PDF]({{page.arch_pdf}})

Below are UML images showcasing our development process for the Final Architecture Design (for Assessment 1).
You can also find all the images here:

[Google Drive: https://drive.google.com/drive/folders/1KE2M_o6zdMu1JZjFEnv3oIxf5IxwgiiC](https://drive.google.com/drive/folders/1KE2M_o6zdMu1JZjFEnv3oIxf5IxwgiiC)

[GitHub: https://github.com/undercooked-team/UnderCooked/tree/main/ENG1/SUBMITTABLES/Architecture](https://github.com/undercooked-team/UnderCooked/tree/main/ENG1/SUBMITTABLES/Architecture)

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
### Addition 1: Game
![Game UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/game.png)
  
We quickly realised how limited our initial UML was surrounding the **gameMaster**:
- **Boot** is responsible for initialising the game.
- **GameSprites** is responsible for holding multiple sprites to render.
- **gameMaster** branched off into 2 classes:
  - **ScreenController** is responsible for switching between the main, pause, instructions, credits, gameplay-screen and game-over screen.
  - **GameScreen** is the gameplay-screen. It contiains various methods and attributes to allow for gameplay, including **mapHelper** related methods.

### Addition 2: Food
![Food UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/food.png)

- **FoodStack** is responsible for holding a stack of in-game cooking ingredients. The chef class will use this object to hold food items.
- **FoodItem** contains information about how to render each ingredient.
- **Recipe** contains each recipe and how each one is made. We learnt that to consrtuct salad and burgers, the cook must hold a certain **FoodStack**. Thus, certain - **FoodStack**s correlate to certain recipes. **Recipe** holds a dict which translates a String recipeName to Array<String> listOfFoodStacks which are all the foodStacks which correlate to recipe recipeName.

### Addition 3: Cooks
![Cooks UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/cooks.png)

- **Chef** was renamed to **Cook**.
- **Collidable** branched into the **GameEntity** class, which was used as a base class for any item that could exist as an object with a physical position in the game-world. We learnt that classes **Cook** and **Customer** can use the same code in **GameEntity**.
- We made **CookInteractor** because this object is solely responsible for letting a cook interact with other objects. Any other object within the **CookInteractor** Rectangle, is an object that the **Cook** and interact with.

### Addition 4: Interactions
![Interactions UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/interactions.png)

These classes were not present in our initial UML in any form. This class is an information class:
- **Interactions** contained a dict, which accepted an ingredient (foodID), and a station (stationID), and outputted a processed ingredient (foodID). This information doesn't exclusively fit into Food, or Station, so it became a class of its own. This class also contains User-related interactions with the game-world, and would monitor which keys the player was pressing.
- **InputKey** is a helper class of Interactions, aiding with the mapping of keyboard keys being pressed, to controls and output in the game.

### Addition 5: Stations
![Stations UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/stations.png)

- **CookInteractable** allowed a station to become interactable with the cook.
- We quickly realised that we would need more stations to allow the player to have greater ability to manipulate the stack of items that they will hold. For example, if the player accidentally collects 1 too many items, they need to be able to bin the top item (**BinStation**). Or if the player needs to swap items with the other cook, they need to be able to place their FoodStack down (**CounterStation**).

### Addition 6: Customers
![Customers UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/customers.png)

These classes were also not present in our initial UML, and we realised this a while ago too, that we need customers to allow for the gameplay specified in the User Requirements. However, this was also the last thing we added since all the other classes above can be tested with each other without needing customers.
- **Customer** is similar to **Cook**, in that it holds cruical information about the customer, like the recipe/request they want.
- **CustomerController** is a new class dedicated to controlling the flow of customers into the restaurant.
  
### Addition 7: Helper
![Helper UML](https://raw.githubusercontent.com/undercooked-team/UnderCooked/main/ENG1/SUBMITTABLES/Architecture/helper.png)
  
- **Hud** is a superclass which easily allows for the addition of information on the screen during gameplay.
- **GameHud** displays the number of customers remaining, as well as a time taken to complete the task.
- **InstructionHud** displays the controls during gameplay, so the player always has a guide to tell them what to do next.
- None of the other Helper classes were orignally added into the initial UML, as none of them directly correlated to a user requirement. Rather, these classes were gradually added throughout development to allow for other classes to function properly, letting the other classes complete their assigned user requirement.
- **Constants** simply contained some global values.
  
## Method Selection and Planning
[Method Selection and Planning Google Doc](https://docs.google.com/document/d/1KpzhVRxdkBJPyYyQxW3aTuWMCYpDrK8jtEIiyoh-2Rw/edit?usp=share_link)

[Method Selection and Planning PDF]({{page.plan_pdf}})

## Risk Assessment and Mitigation
[Risk Assessment and Mitigation Google Doc](https://docs.google.com/document/d/1-oQ4ajU6W9XvK9kMU9fiHc9EGryhhc9Awz3sKuWzotQ/edit?usp=share_link)

[Risk Assessment and Mitigation PDF]({{page.risk_pdf}})

## Implementation
[Implementation Google Doc](https://docs.google.com/document/d/1rC9LAZ9OATbIcQgagMG_clQ7LpItRECm9ADfuPLFHQc/edit?usp=share_link)

[Implementation PDF]({{page.impl_pdf}})