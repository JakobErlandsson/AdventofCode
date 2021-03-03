input = File.read("dayThree.txt").split("\n")
wire1 = input[0].split(",")
wire2 = input[1].split(",")
h = Hash.new
distancesTravelledByBothWiresWhenTheyCollide = []
collisions = []

Point = Struct.new(:x, :y)

curX = curY = 0

h.store(Point.new(curX, curY), 0)
counter = 1
wire1.each{
    |x|
    dir = x[0]
    steps = x[1..x.length].to_i
    for i in 1..steps do
        case dir
        when "R" 
            curX += 1
        when "L" 
            curX -= 1
        when "U" 
            curY -= 1
        else
            curY += 1
        end
        h.store(Point.new(curX, curY), counter)
        counter += 1
    end
}

counter = 1
curX = curY = 0
wire2.each{
    |y|
    dir = y[0]
    steps = y[1..y.length].to_i
    for i in 1..steps do
        case dir
        when "R" 
            curX += 1
        when "L" 
            curX -= 1
        when "U" 
            curY -= 1
        else
            curY += 1
        end
        p = Point.new(curX, curY)
        if h[p] != nil
            distancesTravelledByBothWiresWhenTheyCollide << h[p] + counter
            collisions << (curX).abs + (curY).abs
        end
        counter += 1
    end
}

shortest = collisions[0]
collisions.each{
    |x|
    shortest = [shortest, x].min
}
puts "Day three part one: #{shortest}"

shortest = distancesTravelledByBothWiresWhenTheyCollide[0]
distancesTravelledByBothWiresWhenTheyCollide.each{
    |x|
    shortest = [shortest, x].min
}
puts "Day three part two: #{shortest}"