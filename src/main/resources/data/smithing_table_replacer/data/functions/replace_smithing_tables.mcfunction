# replace_smithing_tables.mcfunction (updated)
# This function replaces Smithing Tables and places the block in the direction the player is facing

execute as @e[type=minecraft:villager] at @s run execute if block ~ ~-1 ~ minecraft:smithing_table run setblock ~ ~-1 ~ sawmill:sawmill[facing=~facing_direction~]
