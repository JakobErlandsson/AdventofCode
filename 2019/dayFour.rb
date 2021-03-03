lower = 138307
upper = 654504

puts upper-lower

count1 = 0
count2 = 0
for i in lower..upper do
    i = i.to_s
    if i[0] > i[1] || i[1] > i[2] || i[2] > i[3] || i[3] > i[4] || i[4] > i[5]
        next
    end
    occurences = []
    10.times.each{|k| occurences << 0}
    for j in 0..5 do
        index = i[j].to_i
        occurences[index] += 1
    end
    one = two = false
    occurences.each{
        |x|
        if x == 2
            two = true
        end
        if x >= 2
            one = true
        end
    }
    if one 
        count1 += 1
    end
    if two 
        count2 += 1
    end
end

puts "Day four part one: #{count1}"
puts "Day four part two: #{count2}"
    
