input = File.read("daySix.txt").split("\n")

def getPath(planet)
    path = []
    while planet != "COM"
        path << $orbits[planet]
        planet = $orbits[planet]
    end
    return path
end

def getFirstCommon(you, santa)
    for i in 0..you.length
        for j in 0..santa.length
            if you[i] == santa[j]
                return i+j
            end
        end
    end
end

$orbits = Hash.new
input.each{
    |x|
    p1 = x.split(")")[0]
    p2 = x.split(")")[1]
    $orbits.store(p2,p1)
}

sum = 0
$orbits.each_key{
    |x|
    current = x
    checkSum = 0
    while current != "COM"
        checkSum += 1
        current = $orbits[current]
    end
    sum += checkSum
}

puts "Day six part one: #{sum}"
puts "Day six part two: #{getFirstCommon(getPath("YOU"), getPath("SAN"))}"



