// This is a functional programm to determine the statistics for a Dungeons and Dragons (DnD) character.
// A character has six statistics (Strenght, Intelligence, Charisma... etc) that are determined by rolling dice in the beginning of the game.
// Each statistic is determined by rolling four 6-sided dice, discarding the lowest result and summing the others.

// Roll statistics for a character
let dndStats = () => {
    let statNames = ["Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"]
    let statScores = []
    for (let i = 0; i < statNames.length; i++){
        console.log(statNames[i]);
        let result = sumSeveralDiceRolls(rollSeveralDiceDiscardLowest(4,6));
        statScores.push(result)
    }
    return statScores;
}

// Roll one dice with given number of sides
let rollDiceWithSides = (sides) => {
    let result = Math.trunc(Math.random() * sides + 1);
    console.log("Rolling one " + sides + "-sided dice: " + result);
    return result; 
}

// Roll several dice with given number of sides
let rollSeveralDice = (amount, sides) => {
    let output = [];
    for(i = 0; i < amount; i++){
        output.push(rollDiceWithSides(sides))
    }
    console.log("Rolling " + amount + " " + sides + "-sided dice: " + output )
    return output;
}

// Roll several dice with given number of sides, discard lowest
let rollSeveralDiceDiscardLowest = (amount, sides) => {
    let diceResults = rollSeveralDice(amount, sides)
    let resultArray = diceResults.sort().filter((_,i) => i)
    console.log("Results without smallest number: " + resultArray)
    return resultArray;
}

// Sum the result of several dice rolls
let sumSeveralDiceRolls = (diceResultArray) => {
    let sum = diceResultArray.reduce((accumulator, currentValue) => accumulator + currentValue);
    console.log("Sum: " + sum)
    return sum;
}



