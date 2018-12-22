import items, enemies, actions, world, os, time, sys, display
 
class MapTile:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.nonUpperClassRooms = 5 

    def intro_text(self):
        raise NotImplementedError()
 
    def modify_player(self, player):
        raise NotImplementedError()

    def adjacent_moves(self):
        """Returns all move actions for adjacent tiles."""
        moves = []
        if world.tile_exists(self.x + 1, self.y):
            moves.append(actions.MoveEast())
        if world.tile_exists(self.x - 1, self.y):
            moves.append(actions.MoveWest())
        if world.tile_exists(self.x, self.y - 1):
            moves.append(actions.MoveNorth())
        if world.tile_exists(self.x, self.y + 1):
            moves.append(actions.MoveSouth())
        return moves
 
    def available_actions(self):
        """Returns all of the available actions in this room."""
        moves = self.adjacent_moves()
        moves.append(actions.ViewInventory())
 
        return moves

# Unused class, consider deleting 
class LootRoom(MapTile):
    def __init__(self, x, y, item):
        self.item = item
        super().__init__(x, y)
 
    def add_loot(self, player):
        player.inventory.append(self.item)
 
    def modify_player(self, player):
        self.add_loot(player)

class EnemyRoom(MapTile):
    def __init__(self, x, y, enemy):
        self.enemy = enemy
        super().__init__(x, y)
 
    def modify_player(self, the_player):
        if self.enemy.is_alive():
            the_player.hp = the_player.hp - self.enemy.damage
            display.slow_print("Enemy does {} damage.".format(self.enemy.damage))
            print("")

    def available_actions(self):
        if self.enemy.is_alive():
            return [actions.Attack(enemy=self.enemy), actions.Flee(tile=self)]
        else:
            return self.adjacent_moves()

# Initial room the player begins at
class TownHall(MapTile):
    # Override the intro_text method in the superclass
    def intro_text(self):
        return """
        Location: Town Hall
        A generator sits at its center no longer running. 
        People are running around in panic and disarray looking
        for the missing generator pieces.
        """
 
    def modify_player(self, player):
        # Room has no action on player
        pass

class Tunnel(MapTile):
    def intro_text(self):
        return """
        Location: Tunnel
        An unremarkable path of the tunnel. The cold hits
        Jack and he can feel his body becoming numb, but
        Jack must forge onwards. 
        """

    def modify_player(self, player):
        player.hp -= 5 

# Maptile adjacent to Town Hall that causes Jack to black out if he has all 3 pieces
class BlackoutTunnel(MapTile):
    def intro_text(self):
        return """
        Location: Tunnel
        An unremarkable path of the tunnel. The cold hits
        Jack and he can feel his body becoming numb, but
        Jack must forge onwards. 
        """

    def modify_player(self, player):
        player.hp -= 5

        if player.generatorPiecesObtained == 3:
            if player.blackouts < 2:
                # Discard generator part 3
                for item in player.inventory:
                    if item.name == items.GeneratorPart3(0).name:
                        player.inventory.remove(item)
                        player.generatorPiecesObtained -= 1
                if player.blackouts == 0:
                    display.slow_print("""
        Hilda, I'm feeling really tired. My mind is starting to 
        go blank. I--
                """)
                    time.sleep(3)
                    os.system('cls')
                    time.sleep(3)

                    for x in range(0, 1000):
                        print("HEHEHE", end="") 
                
                    sys.stdout.flush()
                    time.sleep(2)
                    os.system('cls')
                    time.sleep(3)
                    # Spawn player 
                    player.location_x = 5
                    player.location_y = 4
                    player.blackouts += 1 

                elif player.blackouts == 1:
                    display.slow_print("""
        Hilda, it's happening again. I feel weak. Make it sto--
                """)
                    time.sleep(3)
                    os.system('cls')
                    time.sleep(3)

                    for x in range(0, 1000):
                        print("DIEDIE", end="") 
                
                    sys.stdout.flush()
                    time.sleep(2)
                    os.system('cls')
                    time.sleep(3)
                    # Spawn player 
                    player.location_x = 5
                    player.location_y = 5
                    player.blackouts += 1 

                display.slow_print("""
       Location: ???
       Jack slowly opens his eyes. He notices that one of
       the generator pieces is missing.

       Hilda, what happened to me?
                """)

class ResidentialArea(MapTile):
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.finalCheck = False

    def intro_text(self):
        return """
        Location: Residential Area
        Rows of poor and working class houses can be seen. A strong odor
        fills Jack's nose. Sickly people coughing and wheezing are walking
        around.
        """

    def modify_player(self, player):
        if player.blackouts == 2:
            if self.finalCheck == False:
                player.nonUppClassAreaRoomsVisited += 1
                self.finalCheck = True

# A maptile that contains a generator piece
# Redo to extend LootRoom if possible
class JacksHut(MapTile):
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.itemAvailable = True
        self.finalCheck = False

    def intro_text(self):
        return """
        Location: Jack's Hut
        A messy hut is revealed as Jack enters his home. A 
        once clean and tidy hut is now the home to old dishes
        lying on the table and dirty clothes thrown on his couch.
        Jack sits down at his bed and finds himself at ease.
        """

    def modify_player(self, player):
        if self.itemAvailable:
            display.slow_print("""
        Jack finds a missing piece of the generator.
            """)
            player.inventory.append(items.GeneratorPart1(0))
            self.itemAvailable = False
        
        if player.blackouts == 2:
            if self.finalCheck == False:
                player.nonUppClassAreaRoomsVisited += 1
                self.finalCheck = True

        player.hp = 100

class UpperClassGate(MapTile):
    def intro_text(self):
        return """
        Location: Upper Class Gate
        A guard blocks your path. "Get out of here peasant." exclaims
        the guard.
        """

    def modify_player(self, player):
        self.playerVisits = player.nonUppClassAreaRoomsVisited
        self.blackouts = player.blackouts
    
    # Override super class to not include movement to Upper Class Area
    def adjacent_moves(self):
        """Returns all move actions for adjacent tiles."""
        moves = []
        if world.tile_exists(self.x + 1, self.y):
            moves.append(actions.MoveEast())
        if world.tile_exists(self.x - 1, self.y):
            moves.append(actions.MoveWest())
        if world.tile_exists(self.x, self.y + 1):
            moves.append(actions.MoveSouth())

        # Modify room to allow player to enter Upper Class Area
        if self.blackouts == 2:
            display.slow_print("""
        Jack tells the guards he needs to go through to continue his
        search, but the they tell him to search around town first.
            """)
            if self.nonUpperClassRooms == self.playerVisits:
                if world.tile_exists(self.x, self.y - 1):
                    moves.append(actions.MoveNorth())
                display.slow_print("""
        After telling the guards that the missing piece is not in town,
        the guards exclaim, "You may pass".
                """)
            else:
                display.slow_print("""
        You must leave.
                """)

        return moves

class RichSnobRoom(EnemyRoom):
    def __init__(self, x, y):
        super().__init__(x, y, enemies.RichSnob())

    def intro_text(self):
        if self.enemy.is_alive():
            return """
        Location: Upper Class Area
        The air somehow smells much cleaner than the rest of the town and
        the tunnel floor is smoothed out and carpeted. Well dressed people 
        are walking about complaining about the lack of heat.

        A rich snob appears and attacks Jack!
            """
        else:
            return """
        "You'll pay for this!" cries the rich snob.
            """

class UpperClassArea(MapTile):
    def intro_text(self):
        return """
        The air somehow smells much cleaner than the rest of the town and
        the tunnel floor is smoothed out and carpeted. Well dressed people 
        are walking about complaining about the lack of heat.
        """

    def modify_player(self, player):
        pass 

# End game
class FinalRoom(MapTile):
    def intro_text(self):
        return """
        Location: Upper Class Area
        Jack sees the last generator piece in the corner of his eye and rushes
        over excitedly. "About time, hurry up and fix that generator peasant", 
        cries one of the nobles that sees you. An unnatural smile forms on Jack's
        face as he begins to lose consciousness.
        """

    def modify_player(self, player):
        player.inventory.append(items.GeneratorPart3(0))
        time.sleep(3)
        os.system('cls')
        time.sleep(3)

        for x in range(0, 1000):
            print("HAHAHA", end="") 
                
        sys.stdout.flush()
        time.sleep(2)
        os.system('cls')
        time.sleep(3)

        display.slow_print("Jack's HP:".format(player.hp))
        display.slow_print("Generator Parts Obtained: {}".format(player.generatorPiecesObtained))
        display.slow_print("\nHilda, what should I do?")
        display.slow_print("...")
        time.sleep(1)
        display.slow_print("...")
        time.sleep(1)
        display.slow_print("...")
        time.sleep(1)
        display.slow_print("But your name isn't Hilda, is it {}?".format(os.getlogin()))
        display.slow_print("You've only just got here, but you don't know how twisted this world is.")
        display.slow_print("They took everything from me and now they'll pay. They all will.")
        print("")
        display.slow_print("""
        Jack takes a piece of the generator and smashes it onto the ground repeatedly as the nobles 
        look on in horror. As word gets around that the generator can no longer be repaired, people
        begin trying to leave the town. However, they find the exit to their city has been blocked off
        by a cave in. With nowhere left to turn, despair begins to creep in to people's minds. The coldness 
        begins to settle in. Within a week, everyone dies from sickness and the cold.
        """)
        display.slow_print("""
                                               ----------- 
                                              |  The End  |
                                               -----------
        """)
        player.victory = True

class MarketPlace(MapTile):
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.resetItemAvailable = True
        self.finalCheck = False

    def intro_text(self):
        return """
        Location: Market Place
        Many different types of posts from potato shops to gear vendors are
        littered throughout the area. People are scrambling to see if any
        of the missing gear pieces are here.
        """

    def modify_player(self, player):
        if player.blackouts == 1:
            if self.resetItemAvailable:
                display.slow_print("""
        Jack recovers the missing generator piece.
                """)
                player.inventory.append(items.GeneratorPart3(0))
                self.resetItemAvailable = False
        if player.blackouts == 2:
            if self.finalCheck == False:
                player.nonUppClassAreaRoomsVisited += 1
                self.finalCheck = True

# A maptile that contains a generator piece
# Redo to extend LootRoom if possible
class Spring(MapTile):
    def __init__(self, x, y):
        self.x = x 
        self.y = y
        self.itemAvailable = True
        self.finalCheck = False

    def intro_text(self):
        return"""
        Location: Spring
        A large spring with clean crystal water. Jack stares at 
        his messy reflection off the surface. He dips his hands
        into the cool water and drinks from it feeling
        revitalized. 
        """

    def modify_player(self, player):
        if player.hp + 50 > 100:
            player.hp = 100
        else:
            player.hp += 50

        if self.itemAvailable:
            display.slow_print("""
        Jack finds a missing piece of the generator.
            """)
            player.inventory.append(items.GeneratorPart2(0))
            self.itemAvailable = False
        if player.blackouts == 2:
            if self.finalCheck == False:
                player.nonUppClassAreaRoomsVisited += 1
                self.finalCheck = True

class RatRoom(EnemyRoom):
    def __init__(self, x, y):
        super().__init__(x, y, enemies.GiantRat())

    def intro_text(self):
        if self.enemy.is_alive():
            return """
            Location: Tunnel
            Large footprints are tracked across the tunnel floor
            and ceiling. Jack begins to hear loud squeaking noises. 

            A giant rat appears and attacks!
            """
        else:
            return """
            A corpse of the dead rat is all that remains.
            """

class Farm(MapTile):
    def __init__(self, x, y):
        self.x = x
        self.y = y 
        self.itemAvailable = True
        self.finalCheck = False

    def intro_text(self):
        return """
        Location: Farm
        Potatos. Potatos everywhere.
        """

    def modify_player(self, player):
        if(self.itemAvailable):
            display.slow_print("""
        Jack finds a missing piece of the generator.
            """)
            player.inventory.append(items.GeneratorPart3(0))
            self.itemAvailable = False 
        if player.blackouts == 2:
            if self.finalCheck == False:
                player.nonUppClassAreaRoomsVisited += 1
                self.finalCheck = True

class TunnelExit(MapTile):
    def intro_text(self):
        return """
        Location: Tunnel Exit
        The end of the tunnel that leads to the tunnel highway. The next
        town over is a 5 day trek.
        """

    def modify_player(self, player):
        if player.blackouts < 1:
            display.slow_print("""
        Two guards tell Jack that no one has entered or exited the generator
        pieces went missing. No one is allowed past this point.
            """)
        else:
            display.slow_print("""
        Two guards lay on the floor lifeless. The exit out of town is blocked off by
        dirt and rubble from an explosion.
            """)