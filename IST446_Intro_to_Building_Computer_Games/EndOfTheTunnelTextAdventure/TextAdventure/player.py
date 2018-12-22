import items, world, random, display
 
class Player():
    def __init__(self):
        self.inventory = [items.Gold(15), items.Rock()]
        self.hp = 100
        self.maxHP = 100
        self.location_x, self.location_y = world.starting_position
        self.victory = False
        self.blackouts = 0
        self.generatorPiecesObtained = 0
        self.nonUppClassAreaRoomsVisited = 0
 
    def is_alive(self):
        return self.hp > 0
 
    def print_inventory(self):
        display.slow_print("\n\nInventory:")
        for item in self.inventory:
            print(item, '\n')
    
    def move(self, dx, dy):
        self.location_x += dx
        self.location_y += dy
        display.slow_print(world.tile_exists(self.location_x, self.location_y).intro_text())
 
    def move_north(self):
        self.move(dx=0, dy=-1)
 
    def move_south(self):
        self.move(dx=0, dy=1)
 
    def move_east(self):
        self.move(dx=1, dy=0)
 
    def move_west(self):
        self.move(dx=-1, dy=0)

    def attack(self, enemy):
        best_weapon = None
        max_dmg = 0
        for i in self.inventory:
         if isinstance(i, items.Weapon):
            if i.damage > max_dmg:
                max_dmg = i.damage
                best_weapon = i
 
        display.slow_print("Jack uses {} against {}!".format(best_weapon.name, enemy.name))
        enemy.hp -= best_weapon.damage
        if not enemy.is_alive():
            display.slow_print("Jack defeated {}!".format(enemy.name))
        else:
            display.slow_print("{} HP is {}.".format(enemy.name, enemy.hp))
        print("")

    def do_action(self, action, **kwargs):
     action_method = getattr(self, action.method.__name__)
     if action_method:
                action_method(**kwargs)

    def flee(self, tile):
        """Moves the player randomly to an adjacent tile"""
        available_moves = tile.adjacent_moves()
        r = random.randint(0, len(available_moves) - 1)
        self.do_action(available_moves[r])