// This is a functional programm to create two characters with statistics and let them fight each other until one is at 0 HP
// Each statistic is determined by rolling four 6-sided dice, discarding the lowest result and summing the others.

//Es gibt keine mutable Variablen. 
//const bedeutet Variable kann nicht redeklariert werden, Object.freeze bedeutet Werte können nicht geändert werden. 
const statNames = Object.freeze(["Health", "Attack", "Defence"]);





// Roll statistics for a character
const dndStats = () => {
    return statNames.map(() => {
        let result = sumSeveralDiceRolls(rollSeveralDiceDiscardLowest(4,6));
        return result;
    });
};

// Roll one dice with given number of sides
const rollDiceWithSides = (sides) => {
    let result = Math.trunc(Math.random() * sides + 1);
    return result; 
}

// Roll several dice with given number of sides
const rollSeveralDice = (amount, sides) => {
    let output = [];
    for(i = 0; i < amount; i++){
        output.push(rollDiceWithSides(sides))
    }
    return output;
}

// Roll several dice with given number of sides, discard lowest
const rollSeveralDiceDiscardLowest = (amount, sides) => {
    let diceResults = rollSeveralDice(amount, sides)
    let resultArray = diceResults.sort().filter((_,i) => i)
    return resultArray;
}

// Sum the result of several dice rolls
const sumSeveralDiceRolls = (diceResultArray) => {
    // Deklarativ die Summe errechnen, mit der Higher Order .reduce() Funktion
    let sum = diceResultArray.reduce((accumulator, currentValue) => accumulator + currentValue);
    return sum;
}

 
// Konzepte der Funktionalen Programmierungen
// 1. Only functions
/*  Do not use objects, only functions that operate on data */
// 2. No side effects
/* Die funktionen sind nicht von globalen Variablen die sich ändern können abhängig.
    Allerdings wird die Math.random Funktion von JavaScript benutzt, da mir kein anderes Spiel eingefallen ist,
    das ich in der Zeit realistisch umsetzten kann. */
// 3. Higher order functions

// 4. No variables
// 5. No sequence of commands



