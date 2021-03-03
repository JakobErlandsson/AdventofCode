def total(fuel)
    sum = 0
    while fuel > 0
        fuel = [(fuel/3)-2, 0].max
        sum += fuel
    end
    return sum
end

# lines = readFile("dayOne.txt")


sum1 = sum2 = 0
puts File.read("dayOne.txt").split("\n").map(&:to_i).each{ |x| sum1 += (x.to_i / 3) - 2}
# puts "Day one part one: #{sum}"

# lines.each{ |x| sum2 += total(x.to_i)}
# puts "Day two part two: #{sum}"
    
