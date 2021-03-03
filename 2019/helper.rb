def readFile(filename)
    lines = []
    text=File.open(filename).read
    text.each_line do |line|
        line.gsub!(/(\n*)$/, '')
        lines.push(line)
    end    
    return lines
end