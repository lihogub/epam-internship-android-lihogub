package ru.lihogub.epam_internship_android_lihogub

object Api {
    private val recipes = mapOf(
        52992 to Dish(
            name = "Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots",
            cuisine = "American",
            ingridients = "Potatoes 5 \nCarrots 12 ounces \nScallions 1 \nGarlic 2 cloves \nBread 1 Slice \nGarlic Powder 1 \nSoy Sauce 2 \nGround Beef 1 \nVegetable Oil 1 tsp  \nSugar 2 tsp \nButter 2 tbsp",
            image = R.drawable.meal_preview
        ),
        52935 to Dish(
            name = "Steak Diane",
            cuisine = "French",
            ingridients = "Canola Oil 2 tbs \nBeef Fillet 4 \nBeef Stock 1 1/2 cup  \nButter 2 tbs \nGarlic 2 cloves minced \nChallots 1 medium finely diced \nMushrooms 4 oz  \nBrandy ¼ cup \nHeavy Cream ¼ cup \nDijon Mustard 1 tbs \nWorcestershire Sauce 1 tbs \nTabasco Sauce Dash \nParsley 1 tbs minced \nChives 1 tbs minced \nSalt to taste \nPepper to taste",
            image = R.drawable.steak_preview
        )
    )

    fun getRecipeById(id: Int): Dish {
        return recipes[id] ?: Dish("Not found","Not found","Not found", 0)
    }
}