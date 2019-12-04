#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <future>
#include <thread>
#include <vector>
#include <mutex>
#include <algorithm>

struct Solver
{
    std::vector<int> values;
    int halfSum;
    std::atomic<int> found;
    std::vector<bool> solution;

    void solve(unsigned nrThreads)
    {
        found = 0;
        std::vector<bool> subset(values.size(), false);
        search(subset, 0, halfSum, nrThreads);
    }

    void search(std::vector<bool>& subset, size_t pos, int targetSum, unsigned nrThreads)
    {
        if(found != 0) return;
        if(pos >= values.size()) {
            if(targetSum == 0) {
                // solution found
                int wasFound = found.fetch_or(1);
                if(wasFound == 0) {
                    // we are the first, so we shall record our solution
                    solution = subset;
                }
            }
            return;
        }
        if(nrThreads > 1) {
            std::vector<bool> subset2 = subset;
            subset2[pos] = false;
            std::future<void> other = std::async([this, pos, &subset2, targetSum, nrThreads](){search(subset2, pos+1, targetSum, nrThreads/2);});
            subset[pos] = true;
            search(subset, pos+1, targetSum-values[pos], nrThreads-nrThreads/2);
            other.wait();
        } else {
            subset[pos] = false;
            search(subset, pos+1, targetSum, 1);
            subset[pos] = true;
            search(subset, pos+1, targetSum-values[pos], 1);
        }
    }
};

// Read a vector of integers from stdin. Try to partition into two subsets such that the sum of the numbers in the two subsets is equal.
int main(int argc, char** argv)
{
    long long nrThreads = 2;

    Solver solver;
    int val;
    scanf("%d", &val);
    int sum = val;
    while(val != -1) {

        int r = scanf("%d", &val);
        if(r != 1) {
            if(feof(stdin)) break;
            fprintf(stderr, "Error reading from stdin");
            exit(1);
        }
        solver.values.push_back(val);
        sum += val;
    }
    if(sum % 2 == 1) {
        printf("impossible\n");
        return 0;
    }
    solver.halfSum = sum/2;
    solver.solve(nrThreads);

    if(solver.found) {
        for(unsigned i=0 ; i<solver.values.size() ; ++i) {
            if(solver.solution[i])
            {
                printf("%d ", solver.values[i]);
            }
        }
        printf("\n");
    } else {
        printf("impossible\n");
    }
    return 0;
}