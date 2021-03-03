require_relative 'helper'
$input = File.read("dayTwo.txt").split(',').map(&:to_i)


def runProgram(noun, verb)
    copy = $input.dup
    copy[1] = noun
    copy[2] = verb
    i = 0
    while copy[i] != 99 
        term1 = copy[copy[i+1]]
        term2 = copy[copy[i+2]]
        index = copy[i+3]
        if copy[i] == 1
            copy[index] = term1+term2
        elsif copy[i] == 2
            copy[index] = term1*term2
        end
        i += 4
    end
    return copy[0]
end

target = 19690720
for noun in 0..99 do
    for verb in 0..99 do
        value = runProgram(noun, verb)
        if noun == 12 && verb == 2
            puts "Day one part one: #{value}"
        end
        if value == target
            puts "Day two part two: #{(100 * noun) + verb}"
            break
        end
    end
end

