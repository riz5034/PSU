class Enemy:
    def __init__(self, name, hp, damage):
        self.name = name
        self.hp = hp
        self.damage = damage
 
    def is_alive(self):
        return self.hp > 0

class GiantRat(Enemy):
    def __init__(self):
        super().__init__(name="Giant Rat", hp=10, damage=2)

class RichSnob(Enemy):
    def __init__(self):
        super().__init__(name="Rich Snob", hp=5, damage=1)
