def runProgram(phase, input, prg, i, first)
    for j in prg.length..1000000
        prg[j] = 0
    end
    rbo = 0
    while true
        instruction = prg[i]
        opcode = instruction % 100
        params = [] << (instruction / 100) % 10 << (instruction / 1000) % 10 << (instruction / 10000) % 10
        term1 = params[0] == 0 ? prg[i+1] : params[0] == 1 ? i+1 : prg[i+1] + rbo
        term2 = params[1] == 0 ? prg[i+2] : params[1] == 1 ? i+2 : prg[i+2] + rbo
        term3 = params[2] == 0 ? prg[i+3] : params[2] == 1 ? i+3 : rbo + prg[i+3]
        case opcode
        when 1 
            prg[term3] = prg[term1]+prg[term2]
            i += 4
        when 2 
            prg[term3] = prg[term1]*prg[term2]
            i += 4
        when 3
            prg[term1] = first ? phase : input
            first = false
            i += 2
        when 4
            output = prg[term1]
            puts output
            i += 2
        when 5
            i = prg[term1] != 0 ? prg[term2] : i + 3
        when 6
            i = prg[term1] == 0 ? prg[term2] : i + 3
        when 7
            prg[term3] = prg[term1] < prg[term2] ? 1 : 0
            i += 4
        when 8
            prg[term3] = prg[term1] == prg[term2] ? 1 : 0
            i += 4
        when 9
            rbo += prg[term1]
            i += 2
        when 99
            return output
        end 
    end
end