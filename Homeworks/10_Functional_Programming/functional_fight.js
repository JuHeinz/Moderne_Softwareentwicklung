// Der Wert der Variabeln werden in diesem Programm nicht verändert.
// Dank Object.freeze() können sie auch nicht aus Versehen geändert werden.
let rouge = Object.freeze(
    {
    health: 10,
    attack: 13,
    defence: 10,
    name: "rouge",
}); 

let tank = Object.freeze(
    {
    health: 10,
    attack: 10,
    defence: 12,
    name: "tank",
}); 

/* Two characters fight until one is at 0 hp */
const fight = () => {
    let rougeDefends = isAttackBlockedByDefence(rouge.defence);
    let tankDefends = isAttackBlockedByDefence(tank.defence);
    if (rouge.health < 0 || tank.health < 0){
        console.log("Someone won")
        console.log("Rouge:" + rouge.health); 
        console.log("Tank:" + tank.health);
        
    }else{
        console.log( "Round start! " + "Rouge health: " + rouge.health + " Tank health: " + tank.health);

        /* Tank attacks */
        if(!rougeDefends(tank.attack)){
            console.log("The attack hits")
            rouge = reduceHP(rouge, tank.attack);
        }
        
        /* Rouge attacks */
        if(!tankDefends(rouge.attack)){
            console.log("The attack hits")
            tank = reduceHP(tank, rouge.attack);
        }
        // Rekursiver Aufruf
        fight(); 
    }
}


const reduceHP = (oldCharacter, damageTaken ) => {
    // Damit kein State im Programm ist, wird eine Kopie des Charakters zurückgeben, statt die Variable zu ändern
    let newCharacter = {
        health: oldCharacter.health - damageTaken,
        attack: oldCharacter.attack,
        defence: oldCharacter.defence,
        name: oldCharacter.name
        }
    return newCharacter;
}


//Higher order function
const isAttackBlockedByDefence = (defence) => {
    //Diese Function returned eine Function
    return (attack) => (defence > attack);
}
    
