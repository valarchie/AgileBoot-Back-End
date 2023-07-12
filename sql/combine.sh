#!/bin/bash
# 用于将IDEA导出的不同表sql文件，整合成一份

# Get the current date in the format yyyymmdd
current_date=$(date +%Y%m%d)

# Create the new file name
new_file="agileboot-${current_date}.sql"

# Check if the new file already exists
if [[ -f "$new_file" ]]; then
  # Remove the existing file
  rm "$new_file"
fi

# Loop through all .sql files in the current directory
for file in *.sql
do
  # Replace '`agileboot`.' with an empty string and append the contents to the new file with a blank line after
  sed "s/\`agileboot-pure\`\./ /g" "$file" >> "$new_file"
  echo "" >> "$new_file"
done

echo "Concatenation complete. Output file: $new_file"