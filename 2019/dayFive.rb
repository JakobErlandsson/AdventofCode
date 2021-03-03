$pr = File.read("dayFive.txt").split(",").map(&:to_i)
# prg = "1002,4,3,4,33".split(",").map(&:to_i)

def runProgram(input)
    prg = $pr.dup
    i = 0
    while true
        case prg[i]
        when 1
            prg[prg[i+3]] = prg[prg[i+1]] + prg[prg[i+2]]
            i += 4
        when 2
            prg[prg[i+3]] = prg[prg[i+1]] * prg[prg[i+2]]
            i += 4
        when 3
            prg[prg[i+1]] = input
            i += 2
        when 4
            output = prg[prg[i+1]]
            i += 2
        when 99 
            break
        else
            instruction = prg[i]
            opcode = instruction % 100
            params = [] << (instruction / 100) % 10 << (instruction / 1000) % 10 << (instruction / 10000) % 10
            term1 = params[0] == 0 ? prg[prg[i+1]] : prg[i+1]
            term2 = params[1] == 0 ? prg[prg[i+2]] : prg[i+2]
            term3 = params[2] == 0 ? prg[i+3] : i+3
            case opcode
            when 1 
                prg[term3] = term1+term2
                jump = 4
            when 2 
                prg[term3] = term1*term2
                jump = 4
            when 5
                jump = term1 != 0 ? term2 - i : 3
            when 6
                jump = term1 == 0 ? term2 - i : 3
            when 7
                prg[term3] = term1 < term2 ? 1 : 0
                jump = 4
            when 8
                prg[term3] = term1 == term2 ? 1 : 0
                jump = 4
            end
            i += jump 
        end
    end
    return output
end

puts "Day five part one: #{runProgram(1)}"
puts "Day five part two: #{runProgram(5)}"