// Der Wert der Variabeln werden in diesem Programm nicht verändert.
// Dank Object.freeze() können sie auch nicht aus Versehen geändert werden.
let rouge = Object.freeze(
    {
    health: 10,
    attack: 13,
    defence: 10,
}); 

let tank = Object.freeze(
    {
    health: 10,
    attack: 10,
    defence: 12,
}); 


const fight = () => { /* (Two characters fight until one is at 0 health) */
    let rougeDefends = isAttackBlockedByDefence(rouge.defence);
    let tankDefends = isAttackBlockedByDefence(tank.defence);
    if (rouge.health < 0 || tank.health < 0){
    }else{        
        if(!rougeDefends(tank.attack)){ /* (Tank attacks) */
            rouge = reduceHP(rouge, tank.attack);
        }
         
        if(!tankDefends(rouge.attack)){ /* (Rouge attacks) */
            tank = reduceHP(tank, rouge.attack);
        }

        // Rekursiver Aufruf
        fight(); 
    }
}


const reduceHP = (oldCharacter, damageTaken ) => {
    // Es wird eine Kopie des Charakters zurückgeben, so gibt es keine Abhängigkeit zu globalen Variabeln
    let newCharacter = {
        health: oldCharacter.health - damageTaken,
        attack: oldCharacter.attack,
        defence: oldCharacter.defence,
        }
    return newCharacter;
}


// Higher order function
const isAttackBlockedByDefence = (defence) => {
    // Diese Function returned eine Function
    return (attack) => (defence > attack);
}
    
