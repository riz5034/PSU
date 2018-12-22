import world, items, display, os
from player import Player

def play():
    world.load_tiles()
    player = Player()
    # Display introduction to game
    intro()
    # These lines load the starting room and display the text
    room = world.tile_exists(player.location_x, player.location_y)
    display.slow_print(room.intro_text())
    while player.is_alive() and not player.victory:
        # Check if player has all generator pieces
        generatorPiecesObtained = 0
        for item in player.inventory:
            if item.name == items.GeneratorPart1(0).name:
                generatorPiecesObtained += 1
            elif item.name == items.GeneratorPart2(0).name:
                generatorPiecesObtained += 1
            elif item.name == items.GeneratorPart3(0).name:
                generatorPiecesObtained += 1
        player.generatorPiecesObtained = generatorPiecesObtained

        room = world.tile_exists(player.location_x, player.location_y)
        room.modify_player(player)

        # Check again since the room could have changed the player's state
        if player.is_alive() and not player.victory:
            display.slow_print("Jack's HP: {}".format(player.hp))
            display.slow_print("Generator Parts Obtained: {}".format(player.generatorPiecesObtained))
            display.slow_print("\nHilda, what should I do?\n")
            available_actions = room.available_actions()
            for action in available_actions:
                print(action)
            action_input = input('\nAction: ')
            for action in available_actions:
                if action_input.upper() == action.hotkey:
                    player.do_action(action, **action.kwargs)
                    break
        elif player.hp == 0:
            display.slow_print("""
        Jack feels numb and can no longer move his body. His vision blurs as he falls.
        """)

            display.slow_print("""
        Game Over
            """)
            return

def intro():
    display.slow_print("""
                            ---------------------
                         |    End of the Tunnel    |
                         |  A Text Adventure Game  |
                            ---------------------


        In an alternate 1830’s industrial revolution, our world was 
        hit a brutal ice age. In order for people to survive, they had to 
        relocate to the underground tunnels and catacombs. Although, 
        people banded together for their survival, a caste system forms 
        between those that could afford to bring machinery and resources 
        and those who could not. The upper class citizen had access to 
        basic necessities and lived lavishly while the lower class were 
        farmers that lived in disease filled conditions.

        Our story begins with a man named Jack Hill, a lower class citizen 
        and mechanic of the power generator providing heat for his 
        underground colony. Jack recently lost his wife, Hilda, a servant 
        for an upper class noble a month ago due to poor working conditions. 
        Devastated and without any friends, Jack begins talking to himself 
        as if Hilda, never left him. One day, Jack wakes up to his town in disarray. 
        As Jack makes his way to the Town Hall, he learns that someone has sabotaged 
        the generator and three pieces are missing. Now Jack has to recover the 
        pieces before he and the town die without a heat source.  
        """)

if __name__ == "__main__":
    play()