require_relative 'computer'
$pr = File.read("daySeven.txt").split(",").map(&:to_i)

best = 0
combinations = [0,1,2,3,4].permutation.to_a
combinations.each{
    |c|
    power = 0
    prg = $pr.dup
    for i in 0..4
        power, _, _ = runProgram(c[i], power, prg, 0, true)
    end
    best = [best, power].max
}

puts "Day seven part one: #{best}"

best = 0
combinations = [5,6,7,8,9].permutation.to_a
combinations.each{
    |c|
    prgs = [$pr.dup, $pr.dup, $pr.dup, $pr.dup, $pr.dup]
    index = [0,0,0,0,0]
    power = 0
    phaseNr = 0
    finished = false
    first = true
    loop do
        for i in 0..4
            result = runProgram(c[i], power, prgs[i], index[i], first)
            finished = result == nil
            power = result == nil ? power : result[0]
            index[i] = result == nil ? nil : result[1]
            prgs[i] = result == nil ? nil : result[2] 
        end
        first = false
        break if finished
    end
    best = [best, power].max
}

puts "Day seven part two: #{best}"