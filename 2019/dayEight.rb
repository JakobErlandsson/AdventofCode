input = File.read("dayEight.txt").split("").map(&:to_i)
width = 25
height = 6
sums = []
least = input.length
zeroes = ones = twos = 0
for i in 0..input.length
    if i % (width*height) == 0 && i != 0
        if zeroes <= least
            least = zeroes
            index = (i/150)-1
        end
        sums << ones*twos
        zeroes = ones = twos = 0
    end
    case input[i]
    when 0
        zeroes += 1
    when 1
        ones += 1
    when 2
        twos += 1
    end
end

puts "Day eight part one: #{sums[index]}"

def getColor(i, j)
    for index in 0..$layers.length
        for k in 0..$layers[index].length-1
            case $layers[index][i*25 + j]
            when 0
                return " "
            when 1
                return "*"
            end
        end
    end
end

$layers = []
for i in 0..input.length/(width*height)-1
    $layers[i] = input[i* width * height, (i+1) * width * height - 1]
end

for i in 0..height-1
    for j in 0..width-1
        print getColor(i, j)
    end
    puts 
end






