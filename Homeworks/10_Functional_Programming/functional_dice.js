/*  create a character in a tabletop roleplaying game.
 each statistic is determined by rolling four 6-sided dice, discarding the lowest result and summing the others. */

//Es gibt keine mutable Variablen. 
//const bedeutet Variable kann nicht redeklariert werden, Object.freeze bedeutet Werte können nicht geändert werden. 
const statNames = Object.freeze(["Health", "Attack", "Defence"]);


const dndStats = () => {
    return statNames.map(() => {
        let result = sumSeveralDiceRolls(rollSeveralDiceDiscardLowest(4,6));
        return result;
    });
};


const rollDiceWithSides = (sides) => {
    // Das ist ein Side Effect aber sonst würde das Beispiel keinen Sinn ergeben
    let result = Math.trunc(Math.random() * sides + 1); 
    return result; 
}

const rollSeveralDice = (amount, sides) => {
    let output = [];
    for(i = 0; i < amount; i++){
        output.push(rollDiceWithSides(sides))
    }
    return output;
}

//Die Funktionen sind unabhängig von einander und können deshalb auch leichter getestet werden
const rollSeveralDiceDiscardLowest = (amount, sides) => {
    let diceResults = rollSeveralDice(amount, sides)
    let resultArray = diceResults.sort().filter((_,i) => i)
    return resultArray;
}

const sumSeveralDiceRolls = (diceResultArray) => {
    // Deklarativ die Summe errechnen, mit der Higher Order .reduce() Funktion statt einem for-loop
    let sum = diceResultArray.reduce((accumulator, currentValue) => accumulator + currentValue);
    return sum;
}



